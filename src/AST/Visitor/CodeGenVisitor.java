// Gyorgy Wyatt Muntean, Justin Adsuara Copyright (c) 2017

package AST.Visitor;

import AST.*;
import CodeGen.*;
import Semantics.*;

import java.util.*;
import org.junit.Assert;

/**
 * This class visits the AST of a MiniJava program and generates x86-64 assembly
 * code for the program this AST represents.
 */
public class CodeGenVisitor implements Visitor {
    private AssemblyWriter writer; 
    private MethodOffsetter methodOffsetter;
    private FieldOffsetter fieldOffsetter;
    private Map<String, Integer> localOffsets;
    private UniqueLabeler labeler;
    private static final int QUAD_SIZE = 8;
    private MethodSymbolTable mst;
    private Map<Double, String> doubleLiteralLabels;
    
    // This counter tracks out stack alignment. If the counter is an even number
    // then we are 16-byte aligned, otherwise we are 8-byte aligned.
    private int alignment = 0;
    
    public CodeGenVisitor(Map<Double, String> doubleLiteralLabels) {
        super();
        writer = new AssemblyWriter();

        fieldOffsetter = FieldOffsetter.getInstance();
        methodOffsetter = MethodOffsetter.getInstance();
        localOffsets = null;
        labeler = new UniqueLabeler();
        this.doubleLiteralLabels = doubleLiteralLabels;
    }

    // MainClass m;
    // ClassDeclList cl;
    public void visit(Program n) {
        n.m.accept(this);
        for ( int i = 0; i < n.cl.size(); i++ ) {
            n.cl.get(i).accept(this);
        }
    }
  
    // Identifier i1,i2;
    // Statement s;
    public void visit(MainClass n) {
        writer.gen(".globl asm_main");
        writer.genLabel("asm_main");
        genPrologue();
        n.s.accept(this);
        genEpilogue();
        writer.genLine();
    }

    // Identifier i;
    // VarDeclList vl;
    // MethodDeclList ml;
    public void visit(ClassDeclSimple n) {
        for ( int i = 0; i < n.ml.size(); i++ ) {
            n.ml.get(i).accept(this);
        }
    }
 
    // Identifier i;
    // Identifier j;
    // VarDeclList vl;
    // MethodDeclList ml;
    public void visit(ClassDeclExtends n) {
        for ( int i = 0; i < n.ml.size(); i++ ) {
            n.ml.get(i).accept(this);
        }
    }

    // Type t;
    // Identifier i;
    // FormalList fl;
    // VarDeclList vl;
    // StatementList sl;
    // Exp e;
    public void visit(MethodDecl n) {
        mst = n.mst;
        String className = n.mst.getParentTable().type.className;
        writer.genLabel(className + "$" + n.i.s);
        genPrologue();

        localOffsets = new HashMap<String, Integer>();
        int rbp_offset = 0;
 
        String[] argReg = {"%rsi", "%rdx", "%rcx", "%r8", "%r9"};

        // place the implicit 'this' pointer onto the stack at -8(rbp)
        genPush( "%rdi" );
        rbp_offset += QUAD_SIZE;

        // silly check for argument count
        if( n.fl.size() > 5 ) {
           System.out.println( "Hey you said no more than 5 args :(" );
        }

        // Add each argument to the localOffset map,
        // push each argument onto the stack
        int doubleIndex = 0; // index for doubles
        int nonDoubleIndex = 0; // index for non-double
        for ( int i = 0; i < n.fl.size(); i++ ) {
            Formal f = n.fl.get(i);
            if (f.t.getMiniJavaType().equals(DoubleMJType.getInstance())) {
                genPush("$0"); // dummy value
                writer.gen("movsd %xmm" + doubleIndex + ", (%rsp)");
                doubleIndex++;
            } else {
                genPush( argReg[ nonDoubleIndex ] ); 
                nonDoubleIndex++;
            }
            rbp_offset += QUAD_SIZE;
            String argName = f.i.s;
            localOffsets.put( argName, rbp_offset ); 
        }

        // Visit local variables for this method,
        // reserve spots on the stack for each variable and save the offsets
        // in the localOffsets map. 
        for ( int i = 0; i < n.vl.size(); i++ ) {
            genPush( "$0" );
            rbp_offset += QUAD_SIZE;
         
            String varName = n.vl.get(i).i.s; 
            localOffsets.put( varName, rbp_offset ); 
        }
         
        // visit statements for this method decl
        for ( int i = 0; i < n.sl.size(); i++ ) {
            n.sl.get(i).accept(this);
        }

        // evaluate the return expression, result will be in %rax 
        n.e.accept(this);

        // We have generted all the code for this method.
        // We are now preparing to exit this method, thus 
        // we pop call arguments off stack
        int number_of_locals = rbp_offset / QUAD_SIZE;  // the number of vars we pushed
        for( int i = 0; i < number_of_locals; i++ ) {
           genPop( "%rbx" );   
        }

        genEpilogue();
        // invalidate this method's offset map and method symbol table
        localOffsets = null;
        mst = null;
        writer.genLine();
    }

    // StatementList sl;
    public void visit(Block n) {
        for ( int i = 0; i < n.sl.size(); i++ ) {
            n.sl.get(i).accept(this);
        }
    }

    // Exp e;
    // Statement s1,s2;
    public void visit(If n) {
        String elseLabel = labeler.getUniqueLabel("else");
        String doneLabel = labeler.getUniqueLabel("done");

        n.e.accept(this);
        // 0 or 1 in rax
        writer.gen("cmpq $0, %rax");
        writer.gen("je " + elseLabel);
        n.s1.accept(this);
        writer.gen("jmp " + doneLabel);
        writer.genLabel(elseLabel);
        n.s2.accept(this);
        writer.genLabel(doneLabel);
    }

    // Exp e;
    // Statement s;
    public void visit(While n) {
        String testLabel = labeler.getUniqueLabel("test");
        String doneLabel = labeler.getUniqueLabel("done");
        writer.genLabel(testLabel);
        n.e.accept(this);
        writer.gen("cmpq $0, %rax");
        writer.gen("je " + doneLabel);
        n.s.accept(this);
        writer.gen("jmp " + testLabel);
        writer.genLabel(doneLabel);
    }

    // Exp e;
    public void visit(Print n) {
        n.e.accept(this);
        // at this point, value of e will be in rax
        if (isDouble(n.e)) {
            genPush("%rax");
            writer.gen("movsd (%rsp), %xmm0");
            genPop("%rax");
            genCall("putD");
        } else {
            writer.gen("movq %rax, %rdi");
            genCall("put");
        }
    }
  
    // Identifier i;
    // Exp e;
    public void visit(Assign n) {
        n.e.accept(this);
        // 2 cases: 1. Identifier is a reference to a local variable
        //          2. Identifier references a class variable
        String id = n.i.s;
        if( localOffsets.containsKey( id ) ) {
           // Id is a local variable, grab its offset from the local map
           int stackOffset = localOffsets.get( id );
           writer.gen("movq %rax, -" + stackOffset + "(%rbp)");
        } else {
           // Id is a class variable, we must determine the current class and then grab the var's offset
           ClassSymbolTable cst = mst.getParentTable();
           String className = cst.type.className;
           int fieldOffset = fieldOffsetter.getOffset( className, id );
            
           // put the 'this' pointer in %rbx
           writer.gen( "movq -8(%rbp), %rbx" );
           // chase the 'this' pointer and place %rax into the variable's memory location
           writer.gen( "movq %rax, " + (fieldOffset * QUAD_SIZE) + "(%rbx)" );
        }
    }

    // Identifier i;
    // Exp e1,e2;
    public void visit(ArrayAssign n) {
        String var = n.i.s;
        Integer localOffset = localOffsets.get(var);
        if(localOffset == null) {
            String className = mst.getParentTable().type.className;
            Integer fieldOffset = fieldOffsetter.getOffset(className, var);
            // put this pointer in %rbx
            writer.gen("movq -" + QUAD_SIZE + "(%rbp), %rbx");
            writer.gen("movq " + (fieldOffset * QUAD_SIZE) + "(%rbx), %rax");
            
        } else {
            writer.gen("movq -" + localOffset + "(%rbp), %rax");
        }
        genPush("%rax");
        n.e1.accept(this);
        genPush("%rax");
        n.e2.accept(this);
        genPop("%rbx"); // index
        genPop("%rcx"); // array address
        writer.gen("incq %rbx");
        writer.gen("imulq $" + QUAD_SIZE + ", %rbx"); // offset in %rbx
        writer.gen("movq %rax, (%rcx, %rbx)");
    }

    // Exp e1,e2;
    public void visit(And n) {
        String doneLabel = labeler.getUniqueLabel("done");
        n.e1.accept(this);
        writer.gen("cmpq $0, %rax");
        writer.gen("je " + doneLabel);
        // at this point, e1 will be 1
        // get e2 into rax, 
        n.e2.accept(this);
        writer.genLabel(doneLabel);
    }

    // Exp e1,e2;
    public void visit(LessThan n) { // e1 < e2
        if (isDouble(n)) {
            n.e1.accept(this);
            genPush("%rax");
            writer.gen("movq (%rsp), %xmm0");
            genPop("%rax");
            n.e2.accept(this);
            genPush("%rax");
            writer.gen("movq (%rsp), %xmm1");
            genPop("%rax");
            // initialize %rbx to 0, initialize %rcx to 1
            writer.gen("movq $0, %rbx"); 
            writer.gen("movq $1, %rcx");
            writer.gen("ucomiss %xmm1, %xmm0");
            // e1 in rdx, e2 in rax
            // if e1 < e2, put 1 in $rbx. otherwise, leave 0 there
            writer.gen("cmovl %rcx, %rbx");
            writer.gen("movq %rbx, %rax");
        } else {
            n.e1.accept(this);
            genPush("%rax");
            n.e2.accept(this);
            genPop("%rdx");
            // initialize %rbx to 0, initialize %rcx to 1
            writer.gen("movq $0, %rbx"); 
            writer.gen("movq $1, %rcx");
            // e1 in rdx, e2 in rax
            writer.gen("cmpq %rax, %rdx"); 
            // if e1 < e2, put 1 in $rbx. otherwise, leave 0 there
            writer.gen("cmovl %rcx, %rbx");
            writer.gen("movq %rbx, %rax");
        }
    }

    // Exp e1,e2;
    public void visit(Plus n) {
        if (isDouble(n)) {
            n.e1.accept(this);
            genPush("%rax");
            writer.gen("movsd (%rsp), %xmm0");
            genPop("%rax");
            n.e2.accept(this);
            genPush("%rax");
            writer.gen("movsd (%rsp), %xmm1");
            writer.gen("addsd %xmm0, %xmm1");
            writer.gen("movsd %xmm1, (%rsp)");
            genPop("%rax");
        } else {        
            n.e1.accept(this);
            genPush( "%rax" );
            n.e2.accept(this);
            genPop( "%rdx" );
            writer.gen("addq %rdx, %rax");
        }
    }

    // Exp e1,e2;
    public void visit(Minus n) {
        if (isDouble(n)) {
            n.e1.accept(this);
            genPush("%rax");
            writer.gen("movsd (%rsp), %xmm0");
            genPop("%rax");
            n.e2.accept(this);
            genPush("%rax");
            writer.gen("movsd (%rsp), %xmm1");
            writer.gen("subsd %xmm1, %xmm0");
            writer.gen("movsd %xmm0, (%rsp)");
            genPop("%rax");
        } else {
            n.e1.accept(this);
            genPush( "%rax" );
            n.e2.accept(this);
            genPop( "%rdx" );
            writer.gen("subq %rax, %rdx");
            writer.gen("movq %rdx, %rax");
        }
    }

    // Exp e1,e2;
    public void visit(Times n) {

        if (isDouble(n)) {
            n.e1.accept(this);
            genPush("%rax");
            writer.gen("movsd (%rsp), %xmm0");
            genPop("%rax");
            n.e2.accept(this);
            genPush("%rax");
            writer.gen("movsd (%rsp), %xmm1");
            writer.gen("mulsd %xmm0, %xmm1");
            writer.gen("movsd %xmm1, (%rsp)");
            genPop("%rax");
        } else {
            n.e1.accept(this);
            genPush( "%rax" );
            n.e2.accept(this);
            genPop( "%rdx" );
            writer.gen("imulq %rdx, %rax");
        }
    }

    // Exp e1,e2;
    public void visit(ArrayLookup n) {
        n.e1.accept(this);
        genPush("%rax");
        n.e2.accept(this); // index in %rax
        genPop("%rbx"); // array address
        writer.gen("incq %rax");
        writer.gen("imulq $" + QUAD_SIZE + ", %rax"); // offset in %rax
        writer.gen("movq (%rbx, %rax), %rax");
    }

    // Exp e;
    public void visit(ArrayLength n) {
        n.e.accept(this);
        // length is at offset 0 in the chunk that %rax points to
        writer.gen("movq (%rax), %rax");
    }

    // Exp e;
    // Identifier i;
    // ExpList el;
    public void visit(Call n) {
        int arg_count = 0;
        int norm_count = 0;
        int doub_count = 0;
   
        // Stack to store data types of the arguments we want to pass to a method.
        // A true value in this stack will represent an argument belonging in a 'normal' 
        // register.
        // A false value will represent an argument belonging in the special floating 
        // point registers.
        Stack<Boolean> arg_type = new Stack<Boolean>();
        
        n.e.accept(this);
        // 'this' pointer is in %rax, place it as implicit first argument 
        genPush( "%rax" );
        arg_type.push( new Boolean( true ) );
        norm_count += 1;  // 'this' pointer belongs in a normal register
        arg_count += 1;  // added the implicit 'this' arg

        // visit the arguments, pushing each one onto the stack
        // Also push each arguments register type onto our own data_type stack.
        for( int i = 0; i < n.el.size(); i++ ) {
           Exp arg = n.el.get( i );
           arg.accept(this);
           genPush( "%rax" );
           if( isDouble( arg ) ) {
              arg_type.push( new Boolean( false ) );
              doub_count += 1;
           } else {
              arg_type.push( new Boolean( true ) );
              norm_count += 1;
           } 
           arg_count += 1;
        }

        // We have saved all arguments onto the stack, now we pop them
        // and store them in the correct argument registers.
        while( arg_count > 0 ) {
           // pop the data_type stack to determine which kind of register,
           // we should place the next arg in.
           boolean is_norm_arg = arg_type.pop().booleanValue();
           if( is_norm_arg ) {
              // Normal register case
              switch( norm_count ) { 
                 case 1:   genPop( "%rdi" );
                           break;

                 case 2:   genPop( "%rsi" );
                           break;
   
                 case 3:   genPop( "%rdx" );
                           break;

                 case 4:   genPop( "%rcx" );
                           break;
                  
                 case 5:   genPop( "%r8" );
                           break;
                  
                 case 6:   genPop( "%r9" );
                           break;
              }              
              norm_count -= 1; 
           } else {
              // Special floating point register case
               String reg = "";
               switch( doub_count ) {
               case 1:   reg = "%xmm0";
                   break;

               case 2:   reg = "%xmm1";
                   break;

               case 3:   reg = "%xmm2";
                   break;

               case 4:   reg = "%xmm3";
                   break;
         
               case 5:   reg = "%xmm4";
                   break;
               }

               writer.gen( "movsd (%rsp), " + reg ); // move into right register
               genPop( "%rbx" ); // dummy location
               doub_count -= 1;
           }  // end if-else

           arg_count -= 1; 
        }  // end while

  
       // follow pointer to vtable... and place address of vtable into %rax
       // the 'this' pointer exists in %rdi
       writer.gen( "movq 0(%rdi), %rax" );
       // call the correct method... call methodOffset(%rax)
       
       String className = ((ClassType) n.e.t).className;
       String methodName = n.i.s; 
       int offset = methodOffsetter.getOffset(className, methodName) * QUAD_SIZE;
       genCall("*" + offset + "(%rax)");
    }

    // int i;
    public void visit(IntegerLiteral n) {
        writer.gen("movq $" + n.i + ", %rax");
    }

    public void visit(True n) {
        writer.gen("movq $1, %rax");
    }

    public void visit(False n) {
        writer.gen("movq $0, %rax");
    }

    // String s;
    public void visit(IdentifierExp n) {
        String id = n.s;
        if( localOffsets.containsKey( id ) ) {
            // 'var' was in the local scope, find the stack offset
            int stackOffset = localOffsets.get( id );
            writer.gen("movq -" + stackOffset + "(%rbp), %rax");
        } else {
            // The variable name 'id' is not in local scope.
            // Find the class this variable is a member of and determine its offset.
            String className = mst.getParentTable().type.className;
            int fieldOffset = fieldOffsetter.getOffset( className, id );
            // put the 'this' pointer in %rbx
            writer.gen("movq -" + QUAD_SIZE + "(%rbp), %rbx");
            // follow the 'this' pointer to the variable's location in memory
            writer.gen("movq " + (fieldOffset * QUAD_SIZE) + "(%rbx), %rax");
        }
    }

    public void visit(This n) {
       writer.gen( "movq -8(%rbp), %rax" );
    }

    // Exp e;
    public void visit(NewArray n) {
        n.e.accept(this);
        writer.gen("movq %rax, %rdi");
        genPush("%rax");
        writer.gen("addq $1, %rdi");
        writer.gen("imulq $" + QUAD_SIZE + ", %rdi");
        genCall("mjcalloc");
        genPop("%rbx");
        writer.gen("movq %rbx, (%rax)");
    }

    // Identifier i;
    public void visit(NewObject n) {
        // Grab name of the object
        String name = n.i.s;
        // Grab an extra 8 bytes for the vtable pointer, then 8 bytes for every field
        // in the object. 
        int objectSize = (fieldOffsetter.getObjectSize( name ) + 1) * QUAD_SIZE;
        writer.gen("movq $" + objectSize + ", %rdi");
      
        genCall("mjcalloc");
        writer.gen("leaq " + name + "$$, %rdx");
        writer.gen("movq %rdx, 0(%rax)");
    }

    // Exp e;
    public void visit(Not n) {
        n.e.accept(this);
        writer.gen("xorq $1, %rax");
    }

    /* UNUSED VISITS */

    // TODO
    public void visit(DoubleLiteral n) {
        if (doubleLiteralLabels == null) {
            System.out.println("null map!");
        } else {
            if (doubleLiteralLabels.get(n.x) == null) {
                System.out.println("null entry");
            } else {
                writer.gen("lea " + doubleLiteralLabels.get(n.x) + ", %rbx");
                writer.gen("movq (%rbx), %rax");
            }
        }
    }

    public void visit(DoubleType n) {

    }

    // Type t;
    // Identifier i;
    public void visit(VarDecl n) {

    }

    // Type t;
    // Identifier i;
    public void visit(Formal n) {

    }

    public void visit(IntArrayType n) {

    }

    public void visit(BooleanType n) {

    }

    public void visit(IntegerType n) {

    }

    // String s;
    public void visit(IdentifierType n) {

    }
    
    // String s;
    public void visit(Identifier n) {

    }

    /* code generation helper functions */

    /*
     * @returns true if we are on a 16-byte alignment,
     *          false otherwise (an 8-byte alignment).
     */
    private boolean on16byte() {
       return ((alignment % 2) == 0);
    }

    /*
     * Generates a pushq instruction using 'src' as the data to push.
     * Additionally, increments our stack alignment counter.
     */
    private void genPush( String src ) {
        writer.gen("pushq " + src );
        incrAlign();
    }

    /*
     * Generates a popq instruction, placing the popped data in 'dest'.
     * Additionally, decrements our stack alignment number.
     */
    private void genPop( String dest ) {
        writer.gen( "popq " + dest );
        decrAlign();
    }

    /*
     * if the stack is not 16-byte aligned right now, pushes a dummy value
     * before the call and pops afterward. if the stack is aligned, just
     * generates a call instruction
     */
    private void genCall( String callee ) {
        boolean padded = false;
        if( !on16byte() ) {
           padded = true;
           genPush( "%rbx" );
        }
        writer.gen("call " + callee);
        // pop any padding if we added padding earlier
        if( padded ) {
           genPop( "%rbx" );
        }
    }
    
    /*
     * Generates a function prologue that saves the base pointer and updates it
     */ 
    private void genPrologue() {
        genPush( "%rbp" );
        writer.gen("movq %rsp, %rbp");
    }

    /*
     * Generates a function epilogue that restores the old stack and base pointers
     */
    private void genEpilogue() {
        writer.gen("movq %rbp, %rsp");
        genPop( "%rbp" );
        writer.gen("ret");
    }

    /*
     * A wrapper fucntion for incrementing out alignment counter.
     */  
    private void incrAlign() {
       alignment += 1;
    }

    /*
     * A wrapper for decrementing our alignment counter. This will print a devistating message
     * if we don't have a perfect matching of pushes and pops.
     */ 
    private void decrAlign() {
       alignment -= 1;
       if( alignment < 0 ) {
          System.out.println( "We went past our starting stack location...ooops" );
       }
    }

    // generates a unique label from a given string, to allow labels to be unique
    // but still useful as signposts
    private class UniqueLabeler {
        private int counter;

        public UniqueLabeler() {
            counter = 0;
        }

        /*
         * Generates an unused label that contains the string s
         */
        public String getUniqueLabel(String s) {
            return s + "$L" + counter++;
        }
    }

    private static boolean isDouble(Exp n) {
        return n.t.equals(DoubleMJType.getInstance());
    }
}
