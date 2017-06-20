// Copyright (c) Gyorgy Wyatt Muntean, Justin Adsuara 2017

package AST.Visitor;

import AST.*;
import Semantics.*;
import java.util.List;

/**
 * A visitor used for type checking our MiniJava programs.
 */
public class TypeCheckingVisitor implements Visitor {
    private GlobalSymbolTable gst;
    private MethodSymbolTable mst;
    public boolean allValid;
    
    public TypeCheckingVisitor() {
        super();
        this.gst = GlobalSymbolTable.getInstance();
        mst = null;
        allValid = true;
    }

    private static void expects(String construct, String expected, String actual, int n) {
        System.err.println("line " + n + ": " +
                           construct + " expects " + expected +
                           " but got " + actual);
    }

    private static void operatorExpects( String op, String actual, int n ) {
        System.err.println( "line " + n + ": " +
                            op + " expects " + "(<int>, <int>) or (<double>, <double>)" +
                            " but got " + actual );
    } 
    
    // MainClass m;
    // ClassDeclList cl;
    public void visit(Program n) {
        // 1. check main class
        // 2. check each other class in order
        n.m.accept(this);
        for ( int i = 0; i < n.cl.size(); i++ ) {
            n.cl.get(i).accept(this);
        }
    }
  
    // Identifier i1,i2;
    // Statement s;
    public void visit(MainClass n) {
        // 1. check statement in body of main
        mst = new MethodSymbolTable(gst.getClassST(new ClassType(n.i1.s)));
        n.s.accept(this);
        mst = null;
    }

    // Identifier i;
    // VarDeclList vl;
    // MethodDeclList ml;
    public void visit(ClassDeclSimple n) {
        // 1. check each variable declaration
        // 2. check each method body
        for ( int i = 0; i < n.vl.size(); i++ ) {
            n.vl.get(i).accept(this);
        }
        for ( int i = 0; i < n.ml.size(); i++ ) {
            MethodDecl m = n.ml.get(i);
            m.accept(this);
        }
    }
 
    // Identifier i;
    // Identifier j;
    // VarDeclList vl;
    // MethodDeclList ml;
    public void visit(ClassDeclExtends n) {
        // 1. check each method body
        for ( int i = 0; i < n.vl.size(); i++ ) {
            n.vl.get(i).accept(this);
        }
        for ( int i = 0; i < n.ml.size(); i++ ) {
            MethodDecl m = n.ml.get(i);
            m.accept(this);
        }
    }

    // Type t;
    // Identifier i;
    public void visit(VarDecl n) {
        // 1. visit type
        n.t.accept(this);
    }

    // Type t;
    // Identifier i;
    // FormalList fl;
    // VarDeclList vl;
    // StatementList sl;
    // Exp e;
    public void visit(MethodDecl n) {
        // 1. check that return type exists
        // TODO check each method argument
        // TODO check each variable declaration
        // 2. check each statement
        // 3. check that return value matches return type
        // 4. check if this methodDecl is attempting to override
        //    a super class's methodDecl
        n.t.accept(this);
        mst = n.mst;
        if (mst == null) {
            // SymbolTableVisitor detected this was a duplicate method
            return;
        }
        for ( int i = 0; i < n.fl.size(); i++ ) {
            n.fl.get(i).accept(this);
        }
        for ( int i = 0; i < n.vl.size(); i++ ) {
            n.vl.get(i).accept(this);
        }


        
        for ( int i = 0; i < n.sl.size(); i++ ) {
            Statement s = n.sl.get(i);
            s.accept(this);
        }
        n.e.accept(this);
        if (!canAssign(n.t.getMiniJavaType(), n.e.t)) {
            allValid = false;
            expects("method " + n.i.s + " return value",
                    "a type assignable to " + n.t.getMiniJavaType().toString(),
                    n.e.t.toString(),
                    n.e.line_number);
        }

        MJMethod declType = n.getMiniJavaType();
        MJMethod superType = n.isOverriding();
        if( superType != null ) {
           // this methodDecl does in fact override a super class's decl
           // check that it is a valid override 
           if( !superType.isOverridable( declType ) ) {
              // Not a valid override, types do not match
              allValid = false;  // do I want to set this?
              // ugg this error message is gross
              System.err.println( "line " + n.line_number +  ": method " 
                                  + n.i.s + " type mismatches and cannot " 
                                  + "override super class's method" );
           } 
        }

        mst = null;
    }

    // StatementList sl;
    public void visit(Block n) {
        // 1. check each statement
        for ( int i = 0; i < n.sl.size(); i++ ) {
            n.sl.get(i).accept(this);
        }
    }

    // Exp e;
    // Statement s1,s2;
    public void visit(If n) {
        // 1. check that condition is boolean
        // 2. check true branch
        // 3. check false branch
        n.e.accept(this);
        n.s1.accept(this);
        n.s2.accept(this);
        BoolType b = BoolType.getInstance();
        if(!n.e.t.matches(b)) {
            allValid = false;
            expects("if statement", b.toString(), n.e.t.toString(), n.e.line_number);
        }
    }

    // Exp e;
    // Statement s;
    public void visit(While n) {
        // 1. check that condition is boolean
        // 2. check statement
        n.e.accept(this);
        n.s.accept(this);
        BoolType b = BoolType.getInstance();
        if(!n.e.t.matches(b)) {
            allValid = false;
            expects("while loop", b.toString(), n.e.t.toString(), n.e.line_number);
        }
    }

    // Exp e;
    public void visit(Print n) {
        // 1. check that expression to print is boolean
        n.e.accept(this);
        IntType it = IntType.getInstance();
        DoubleMJType dt = DoubleMJType.getInstance();
        // check that the expression matches either a double or an int
        if ( !n.e.t.matches( it ) && !n.e.t.matches( dt ) ) {
            allValid = false;
            expects( "System.out.println", 
                     it.toString() + " or " + dt.toString(), 
                     n.e.t.toString(),
                     n.e.line_number);
        }
    }

    // returns whether we can assign something of type rhs into a variable
    // of type lhs
    private boolean canAssign(MJType lhs, MJType rhs) {
        // check if same type (covers primitives and identical classes)
        if (lhs.matches(rhs)) {
            return true;
        }
        // check if rhs is a subtype of lhs
        if (lhs instanceof ClassType && rhs instanceof ClassType) {
            return gst.classTree.isSubType((ClassType) rhs, (ClassType) lhs);
        }
        return false;
    }
    
    // Identifier i;
    // Exp e;
    public void visit(Assign n) {
        // 1. check if rhs is subtype of lhs
        n.i.accept(this);
        n.e.accept(this);
        if(!canAssign(n.i.t, n.e.t)) {
            allValid = false;
            expects("assignment",
                    "a type assignable to " + n.i.t.toString(),
                    n.e.t.toString(),
                    n.i.line_number);
        }
    }

    // Identifier i;
    // Exp e1,e2;
    public void visit(ArrayAssign n) {
        // 1. check that i has type IntArray
        // 2. check that e1 (index) has type int
        // 3. check that e2 (element val) has type int
        n.i.accept(this);
        n.e1.accept(this);
        n.e2.accept(this);
        IntArray ia = IntArray.getInstance();
        IntType it = IntType.getInstance();
        if (!n.i.t.matches(ia) ||
            !n.e1.t.matches(it) ||
            !n.e2.t.matches(it)) {
            allValid = false;
            expects("array assign",
                    "(" + ia.toString() + ", " + it.toString() + ", " +
                    it.toString() + ")",
                    "(" + n.i.t.toString() + ", " + n.e1.t.toString() +
                    ", " + n.e2.t.toString() + ")",
                    n.line_number);
        }
    }

    // Exp e1,e2;
    public void visit(And n) {
        // 1. check that e1 is boolean
        // 2. check that e2 is boolean
        n.e1.accept(this);
        n.e2.accept(this);
        BoolType b = BoolType.getInstance();
        if (!n.e1.t.matches(b) ||
            !n.e2.t.matches(b)) {
            allValid = false;
            expects("&& operator",
                    "(" + b.toString() + ", " + b.toString() + ")",
                    "(" + n.e1.t.toString() + ", " + n.e2.t.toString() + ")",
                    n.line_number);
        }
        n.t = BoolType.getInstance();
    }

    // Exp e1,e2;
    public void visit(LessThan n) {
        // 1. check that e1 is an int or double
        //     if e1 is neither, print error
        // 2. check that e2 matches type of e1
        //     if e2 does not match e1, print error
        n.e1.accept(this);
        n.e2.accept(this);

        MJType t1 = n.e1.t;
        MJType t2 = n.e2.t;
        MJType type = typeCheckOperator( t1, t2 );

        if( type == null ) {
           // e1 was neither an int or a double, print an error
           allValid = false;
           operatorExpects( "< operator", 
                    "(" + n.e1.t.toString() + ", " + n.e2.t.toString() + ")",
                    n.line_number );
        } 

        // Assign the type of this node to boolean always
        n.t = BoolType.getInstance();
    }

    // Exp e1,e2;
    public void visit(Plus n) {
        // 1. check that e1 is an int or double
        //     if e1 is neither, print error
        // 2. check that e2 matches type of e1
        //     if e2 does not match e1, print error
        n.e1.accept(this);
        n.e2.accept(this);

        MJType t1 = n.e1.t;
        MJType t2 = n.e2.t;
        MJType type = typeCheckOperator( t1, t2 );

        if( type == null ) {
           // e1 was neither an int or a double, print an error
           allValid = false;
           operatorExpects( "+ operator", 
                    "(" + n.e1.t.toString() + ", " + n.e2.t.toString() + ")",
                    n.line_number );
           // set this type to int and pretend like nothing bad happened.
           n.t = IntType.getInstance();
        } else {
            // Assign the type of this node to the result of our type check
            n.t = type;
        }
    }

    // Exp e1,e2;
    public void visit(Minus n) {
        // 1. check that e1 is an int or double
        //     if e1 is neither, print error
        // 2. check that e2 matches type of e1
        //     if e2 does not match e1, print error
        n.e1.accept(this);
        n.e2.accept(this);

        MJType t1 = n.e1.t;
        MJType t2 = n.e2.t;
        MJType type = typeCheckOperator( t1, t2 );

        if( type == null ) {
           // e1 was neither an int or a double, print an error
           allValid = false;
           operatorExpects( "- operator", 
                    "(" + n.e1.t.toString() + ", " + n.e2.t.toString() + ")",
                    n.line_number );
           // set this type to int and pretend like nothing bad happened.
           n.t = IntType.getInstance();
        } else {
            // Assign the type of this node to the result of our type check
            n.t = type;
        }
    }

    // Exp e1,e2;
    public void visit(Times n) {
        // 1. check that e1 is an int or double
        //     if e1 is neither, print error
        // 2. check that e2 matches type of e1
        //     if e2 does not match e1, print error
        n.e1.accept(this);
        n.e2.accept(this);

        MJType t1 = n.e1.t;
        MJType t2 = n.e2.t;
        MJType type = typeCheckOperator( t1, t2 );

        if( type == null ) {
           // e1 was neither an int or a double, print an error
           allValid = false;
           operatorExpects( "* operator", 
                    "(" + n.e1.t.toString() + ", " + n.e2.t.toString() + ")",
                    n.line_number );
           // set this type to int and pretend like nothing bad happened.
           n.t = IntType.getInstance();
        } else {
            // Assign the type of this node to the result of our type check
            n.t = type;
        }
    }

    // Exp e1,e2;
    public void visit(ArrayLookup n) {
        // 1. check that e1 is an int or double
        //     if e1 is neither, print error
        // 2. check that e2 matches type of e1
        //     if e2 does not match e1, print error
        n.e1.accept(this);
        n.e2.accept(this);
        IntArray ia = IntArray.getInstance();
        IntType it = IntType.getInstance();
        if(!n.e1.t.matches(ia) ||
           !n.e2.t.matches(it)) {
            allValid = false;
            expects("array lookup",
                    "(" + ia.toString() + ", " + it.toString() + ")",
                    "(" + n.e1.t.toString() + ", " + n.e2.t.toString() + ")",
                    n.line_number);
        }
        n.t = IntType.getInstance();
    }

    // Exp e;
    public void visit(ArrayLength n) {
        // 1. check that e is an IntArray
        n.e.accept(this);
        IntArray i = IntArray.getInstance();
        if(!n.e.t.matches(i)) {
            allValid = false;
            expects("array length", i.toString(), n.e.t.toString(),
                    n.e.line_number);
        }
        n.t = IntType.getInstance();
    }

    // Exp e;
    // Identifier i;
    // ExpList el;
    public void visit(Call n) {
        // 1. check type of object
        // 2. check type of method
        // 3. one by one, check that arg n is a subtype of expected arg type n
        n.e.accept(this);

        // Grab the class table associated with this type
        if (!(n.e.t instanceof ClassType)) {
            allValid = false;
            System.err.println("line " + n.line_number + ": method call on non-class type");
            n.t = Bottom.getInstance();
            return;
        }

        ClassSymbolTable cst = gst.getClassST((ClassType) n.e.t);
        if (cst == null) {
            allValid = false;
            n.t = Bottom.getInstance();
            return;
        }
         
        // Get the type of the method from the ClassSymbolTable 
        MJMethod methodType = cst.getMethodType(n.i.s);
        if (methodType == null) {
            System.err.println("line " + n.line_number + ": method " + n.i.s+ " was not declared." );
            allValid = false;
            n.t = Bottom.getInstance();
            return;
        }

        // Check that the expression passed in as arguments are
        // proper subtypes of the method's argument types.
        List<MJType> expectedTypes = methodType.argumentTypes;
        if (n.el.size() != expectedTypes.size()) {
            allValid = false;
            expects("method " + n.i.s,
                    expectedTypes.size() + " arguments",
                    n.el.size() + " arguments",
                    n.line_number);
            n.t = Bottom.getInstance();
            return;
        }
        for ( int i = 0; i < n.el.size(); i++ ) {
            Exp e = n.el.get(i);
            e.accept(this);
            MJType expectedType = expectedTypes.get(i);
            if (!canAssign(expectedType, e.t)) {
                allValid = false;
                System.err.println( "line " + e.line_number + ": argument " +
                                    i + " expected type " + expectedType +
                                    " in method call: " + n.i.s );
                n.t = Bottom.getInstance();
                break;
            }
        }
        n.t = methodType.returnType;
    }

    // int i;
    public void visit(IntegerLiteral n) {
        n.t = IntType.getInstance();
    }

    public void visit(True n) {
        n.t = BoolType.getInstance();
    }

    public void visit(False n) {
        n.t = BoolType.getInstance();
    }

    // String s;
    public void visit(IdentifierExp n) {
        // 1. look up variable in symbol table
        MJType t =  mst.getVarType(n.s);
        if (t == null) {
            allValid = false;
            System.err.println("line " + n.line_number + ": unknown variable " + n.s +
                               " referenced");
            n.t = Bottom.getInstance();
        } else {
            n.t = t;
        }
    }

    public void visit(This n) {
        // 1. look up current class symbol table
        n.t = mst.getParentTable().type;
    }

    // Exp e;
    public void visit(NewArray n) {
        // 1. check that expression for length is an int
        n.e.accept(this);
        IntType i = IntType.getInstance();
        if (!n.e.t.matches(i)) {
            allValid = false;
            expects("length in new int[]", i.toString(), n.e.t.toString(),
                    n.line_number);
        }
        n.t = IntArray.getInstance();
    }

    // Identifier i;
    public void visit(NewObject n) {
        n.t = new ClassType(n.i.s);
    }

    // Exp e;
    public void visit(Not n) {
        // 1. check that e is a bool
        n.e.accept(this);
        BoolType b = BoolType.getInstance();
        if (!n.e.t.matches(b)) {
            allValid = false;
            expects("! operator", b.toString(), n.e.t.toString(), n.line_number);
        }
        n.t = BoolType.getInstance();
    }

    // String s;
    public void visit(Identifier n) {
        // 1. look up in symbol table
        MJType t = mst.getVarType(n.s);
        if (t == null) {
            allValid = false;
            System.err.println("line " + n.line_number + ": unknown variable " + n.s +
                               " referenced");
            n.t = Bottom.getInstance();
        } else {
            n.t = t;
        }
    }

    public void visit(IdentifierType n) {
        MJType t = n.getMiniJavaType();
        if (t instanceof ClassType) {
            ClassType ct = (ClassType) t;
            if(gst.getClassST(ct) == null) {
                allValid = false;
                System.err.println("line " + n.line_number + ": nonexistent class " +
                                   ct.className);
            }
        }
    }

    public void visit(Formal n) {
        n.t.accept(this);
    }

    public void visit( DoubleLiteral n ) {
        n.t = DoubleMJType.getInstance();
    }

    /*
     * Determines if there is a type checking error between two 
     * sides of an operator (*,+,-,<). The return MJType will be the type
     * of the operator expression containing t1 and t2 (IntType by default).
     *
     * @returns The resolved type of the two operands t1 and t2,
     *          null if there is a type checking error.
     */
    private MJType typeCheckOperator( MJType t1, MJType t2 ) {
        IntType it = IntType.getInstance();
        DoubleMJType dt = DoubleMJType.getInstance();
        Bottom bt = Bottom.getInstance();
      
        // Special case. Check if t1 is Bottom.        
        if( t1.equals( bt ) ) {
            if( t2.matches( it ) ) {
               // t2 was an int or bottom, just return IntType
               return it;
            }  
            if( t2.matches( dt ) ) {
               // t2 was a double 
               return dt;
            }     
            // t2 was not a valid type for an operator 
            return null;
        }

        if( t1.matches( it ) ) {
            // e1 is an Integer
            if( !t2.matches( t1 ) ) {
                return null;
            }
            return it;     
        } else if( t1.matches( dt ) ) {
            // e1 is a Double
            if( !t2.matches( t1 ) ) {
                return null;
            }
            return dt;
        } else {
            // t1 was neither an int or a double
            return null;
        }
    }    
    
    // un-used visits
    public void visit(IntArrayType n) { }
    public void visit(BooleanType n) { }
    public void visit(IntegerType n) { }
    public void visit(DoubleType n) {
    }
    
}
