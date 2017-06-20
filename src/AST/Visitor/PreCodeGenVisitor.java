// Gyorgy Wyatt Muntean, Justin Adsuara Copyright (c) 2017

package AST.Visitor;

import AST.*;
import Semantics.*;
import CodeGen.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import org.junit.Assert;

/**
 * This class visits the AST of a MiniJava program and generates x86-64 assembly
 * code for the program this AST represents.
 */
public class PreCodeGenVisitor implements Visitor {
    private AssemblyWriter writer; 
    private FieldOffsetter fieldOffsetter;
    private MethodOffsetter methodOffsetter;
    private int doubleCounter;
    public Map<Double, String> doubleLiteralLabels;
    
    public PreCodeGenVisitor() {
        super();
        writer = new AssemblyWriter();
        fieldOffsetter = FieldOffsetter.getInstance();
        methodOffsetter = MethodOffsetter.getInstance();
        doubleCounter = 0;
        doubleLiteralLabels = new TreeMap<>();
    }

    // MainClass m;
    // ClassDeclList cl;
    public void visit(Program n) {
        n.m.accept(this);
        writer.gen(".data");
        for ( int i = 0; i < n.cl.size(); i++ ) {
            ClassDecl cd = n.cl.get(i);
            cd.accept(this);
        }
        writer.genLine();
        for (Double d : doubleLiteralLabels.keySet()) {
            writer.genLabel(doubleLiteralLabels.get(d));
            writer.gen(".double " + d); 
        }
    }
  
    // Identifier i1,i2;
    // Statement s;
    public void visit(MainClass n) {
        n.s.accept(this);
    }

    private void emitMethodTable( String className, ClassSymbolTable cst ) {
        writer.genLabel(className + "$$");        
        Map<String, Integer> m = methodOffsetter.getMethodOffsetMap(className);
        int size = m.size();
        String[] l = new String[size];

        // set up l so that it contains methods in the order they're to be laid
        // out in the method table
        for (String name : m.keySet()) {
            int i = m.get(name);
            l[i] = name;
        }
        for (String methodName : l) {
            String originalClass = cst.getSourceOfInheritance( methodName );
            writer.gen(".quad " + originalClass + "$" + methodName);
        }
    }
    
    // Identifier i;
    // VarDeclList vl;
    // MethodDeclList ml;
    public void visit(ClassDeclSimple n) {
        emitMethodTable( n.i.s, n.cst );
        for ( int i = 0; i < n.ml.size(); i++ ) {
            n.ml.get(i).accept(this);
        }
    }
 
    // Identifier i;
    // Identifier j;
    // VarDeclList vl;
    // MethodDeclList ml;
    public void visit(ClassDeclExtends n) {
        emitMethodTable( n.i.s, n.cst );
        for ( int i = 0; i < n.ml.size(); i++ ) {
            n.ml.get(i).accept(this);
        }
    }

    // Type t;
    // Identifier i;
    public void visit(VarDecl n) {
        n.t.accept(this);
        n.i.accept(this);
    }

    // Type t;
    // Identifier i;
    // FormalList fl;
    // VarDeclList vl;
    // StatementList sl;
    // Exp e;
    public void visit(MethodDecl n) {
        String className = n.mst.getParentTable().type.className;
        n.i.accept(this);
        n.t.accept(this);
        for ( int i = 0; i < n.fl.size(); i++ ) {
            n.fl.get(i).accept(this);
        }
        for ( int i = 0; i < n.vl.size(); i++ ) {
            n.vl.get(i).accept(this);
        }
        for ( int i = 0; i < n.sl.size(); i++ ) {
            n.sl.get(i).accept(this);
        }
        n.e.accept(this);
    }

    // Type t;
    // Identifier i;
    public void visit(Formal n) {
        n.t.accept(this);
        n.i.accept(this);
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

    // StatementList sl;
    public void visit(Block n) {
        for ( int i = 0; i < n.sl.size(); i++ ) {
            n.sl.get(i).accept(this);
        }
    }

    // Exp e;
    // Statement s1,s2;
    public void visit(If n) {
        n.e.accept(this);
        n.s1.accept(this);
        n.s2.accept(this);
    }

    // Exp e;
    // Statement s;
    public void visit(While n) {
        n.e.accept(this);
        n.s.accept(this);
    }

    // Exp e;
    public void visit(Print n) {
        n.e.accept(this);
    }
  
    // Identifier i;
    // Exp e;
    public void visit(Assign n) {
        n.i.accept(this);
        n.e.accept(this);
    }

    // Identifier i;
    // Exp e1,e2;
    public void visit(ArrayAssign n) {
        n.i.accept(this);
        n.e1.accept(this);
        n.e2.accept(this);
    }

    // Exp e1,e2;
    public void visit(And n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    // Exp e1,e2;
    public void visit(LessThan n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    // Exp e1,e2;
    public void visit(Plus n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    // Exp e1,e2;
    public void visit(Minus n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    // Exp e1,e2;
    public void visit(Times n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    // Exp e1,e2;
    public void visit(ArrayLookup n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    // Exp e;
    public void visit(ArrayLength n) {
        n.e.accept(this);
    }

    // Exp e;
    // Identifier i;
    // ExpList el;
    public void visit(Call n) {
        n.e.accept(this);
        n.i.accept(this);
        
    
        for ( int i = 0; i < n.el.size(); i++ ) {
            n.el.get(i).accept(this);
        }
    }

    // int i;
    public void visit(IntegerLiteral n) {
    }

    public void visit(True n) {
    }

    public void visit(False n) {
    }

    // String s;
    public void visit(IdentifierExp n) {
    }

    public void visit(This n) {
    }

    // Exp e;
    public void visit(NewArray n) {
        n.e.accept(this);
    }

    // Identifier i;
    public void visit(NewObject n) {
        // assuming we're in the body of a function to be called
        int size = 8; // for now, only vtable pointer
        String name = n.i.s;
    }

    // Exp e;
    public void visit(Not n) {
        n.e.accept(this);
    }

    // String s;
    public void visit(Identifier n) {
    }

    // TODO
    public void visit(DoubleLiteral n) {
        String labelPrefix = "double$lit$";
        doubleLiteralLabels.put(n.x, labelPrefix + doubleCounter);
        doubleCounter++;
    }

    public void visit(DoubleType n) {

    }
}
