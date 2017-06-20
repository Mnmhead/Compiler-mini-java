// Gyorgy Wyatt Muntean, Justin Adsuara Copyright (c) 2017

package AST.Visitor;

import AST.*;
import org.junit.Assert;

// Sample print visitor from MiniJava web site with small modifications for UW CSE.
// HP 10/11

public class ASTPrintVisitor implements Visitor {
  public int indent_count;  // tracks our indentation heirarchy

  // Initializes indent count to 0 for this visitor
  public ASTPrintVisitor() {
    super();
    indent_count = 0;
  }

  // Prints count number of indentations (white space)
  private void printIndent() {
    for( int i = 0; i < indent_count; i++ ) {
      System.out.print( "  " );
    } 
  }

  // increments the indent counter
  private void incrIndent() {
    indent_count += 1;
    assert( indent_count >= 0 );    
  }

  // decrements the indent counter
  private void decrIndent() {
    indent_count -= 1;
    assert( indent_count >= 0 );
  }

  // MainClass m;
  // ClassDeclList cl;
  public void visit(Program n) {
    printIndent();
    System.out.println( "Program" );

    incrIndent();

    n.m.accept(this);
    // print class decl list
    for ( int i = 0; i < n.cl.size(); i++ ) {
        n.cl.get(i).accept(this);
    }

    decrIndent();
  }
  
  // Identifier i1,i2;
  // Statement s;
  public void visit(MainClass n) {
    printIndent();
    System.out.print( "MainClass " );
    n.i1.accept(this);
    n.printLineNumber();
    System.out.println();

    incrIndent();

    printIndent();
    n.s.accept(this);
    System.out.println();

    decrIndent();
  }

  // Identifier i;
  // VarDeclList vl;
  // MethodDeclList ml;
  public void visit(ClassDeclSimple n) {
    printIndent();
    System.out.print("Class ");
    n.i.accept(this);
    n.printLineNumber();
    System.out.println();

    incrIndent();

    // print var decl list
    for ( int i = 0; i < n.vl.size(); i++ ) {
        printIndent();
        n.vl.get(i).accept(this);
    }
    // print method decl list
    for ( int i = 0; i < n.ml.size(); i++ ) {
        n.ml.get(i).accept(this);
    }

    decrIndent();
  }
 
  // Identifier i;
  // Identifier j;
  // VarDeclList vl;
  // MethodDeclList ml;
  public void visit(ClassDeclExtends n) {
    printIndent();
    System.out.print("Class ");
    n.i.accept(this);
    System.out.print(" extends ");
    n.j.accept(this);
    n.printLineNumber();
    System.out.println();

    incrIndent();

    // print var decl list
    for ( int i = 0; i < n.vl.size(); i++ ) {
        printIndent();
        n.vl.get(i).accept(this);
    }
    for ( int i = 0; i < n.ml.size(); i++ ) {
        n.ml.get(i).accept(this);
    }

    decrIndent();
  }

  // Type t;
  // Identifier i;
  public void visit(VarDecl n) {
    n.t.accept(this);
    System.out.print(" ");
    n.i.accept(this);
    n.printLineNumber();
    System.out.println();
  }

  // Type t;
  // Identifier i;
  // FormalList fl;
  // VarDeclList vl;
  // StatementList sl;
  // Exp e;
  public void visit(MethodDecl n) {
    printIndent();
    System.out.print("MethodDecl ");
    n.i.accept(this);
    n.printLineNumber();
    System.out.println();

    incrIndent();
    printIndent();
    System.out.print( "returns " );
    n.t.accept(this);
    System.out.println();

    printIndent();
    System.out.println( "parameters:" );

    // print formal list of parameters
    incrIndent();
    for ( int i = 0; i < n.fl.size(); i++ ) {
        printIndent();
        n.fl.get(i).accept(this);
        System.out.println();
    }
    decrIndent();

    // print variable declaration list
    for ( int i = 0; i < n.vl.size(); i++ ) {
        printIndent();
        n.vl.get(i).accept(this);
    }
    // print statement list
    for ( int i = 0; i < n.sl.size(); i++ ) {
        printIndent();
        n.sl.get(i).accept(this);
    }

    printIndent();
    System.out.print("Return ");
    n.e.accept(this);
    n.e.printLineNumber();
    System.out.println();

    decrIndent();
  }

  // Type t;
  // Identifier i;
  public void visit(Formal n) {
    n.t.accept(this);
    System.out.print(" ");
    n.i.accept(this);
  }

  public void visit(IntArrayType n) {
    System.out.print("int []");
  }

  public void visit(BooleanType n) {
    System.out.print("boolean");
  }

  public void visit(IntegerType n) {
    System.out.print("int");
  }

  // String s;
  public void visit(IdentifierType n) {
    System.out.print(n.s);
  }

  // StatementList sl;
  public void visit(Block n) {
    System.out.print( "Statement Block " );
    n.printLineNumber();
    System.out.println();
   
    // print the list of statements in this block
    incrIndent();
    for ( int i = 0; i < n.sl.size(); i++ ) {
        printIndent();
        n.sl.get(i).accept(this);
    }
    decrIndent();
  }

  // Exp e;
  // Statement s1,s2;
  public void visit(If n) {
    System.out.print( "If " );
    n.e.accept(this);
    n.printLineNumber();
    System.out.println();

    incrIndent();
    printIndent();
    n.s1.accept(this);
    decrIndent();
   
    printIndent(); 
    System.out.println( "Else" );
   
    incrIndent();
    printIndent();
    n.s2.accept(this);
    decrIndent();
  }

  // Exp e;
  // Statement s;
  public void visit(While n) {
    System.out.print( "While " );
    n.e.accept(this);
    n.printLineNumber();
    System.out.println();

    incrIndent();
    printIndent();
    n.s.accept(this);
    decrIndent();
  }

  // Exp e;
  public void visit(Print n) {
    System.out.print( "Print " );
    n.e.accept(this);
    n.printLineNumber();
    System.out.println();
  }
  
  // Identifier i;
  // Exp e;
  public void visit(Assign n) {
    System.out.print( "Assign " );
    n.i.accept(this);
    n.printLineNumber();
    System.out.println();

    incrIndent();
    printIndent();
    n.e.accept(this);
    decrIndent();

    System.out.println();
   }

  // Identifier i;
  // Exp e1,e2;
  public void visit(ArrayAssign n) {
    System.out.print( "ArrayAssign " );
    n.i.accept(this);
    System.out.print( "[" );
    n.e1.accept(this);
    System.out.print( "]" );
    n.printLineNumber();
    System.out.println();

    incrIndent();
    printIndent();
    n.e2.accept(this);
    decrIndent();

    System.out.println();
   }

  // Exp e1,e2;
  public void visit(And n) {
    System.out.print( "(" );
    n.e1.accept(this);
    System.out.print( " && " );
    n.e2.accept(this);
    System.out.print( ")" );
  }

  // Exp e1,e2;
  public void visit(LessThan n) {
    System.out.print( "(" );
    n.e1.accept(this);
    System.out.print( " < " );
    n.e2.accept(this);
    System.out.print( ")" );
  }

  // Exp e1,e2;
  public void visit(Plus n) {
    System.out.print( "(" );
    n.e1.accept(this);
    System.out.print( " + " );
    n.e2.accept(this);
    System.out.print( ")" );
  }

  // Exp e1,e2;
  public void visit(Minus n) {
    System.out.print( "(" );
    n.e1.accept(this);
    System.out.print( " - " );
    n.e2.accept(this);
    System.out.print( ")" );
  }

  // Exp e1,e2;
  public void visit(Times n) {
    System.out.print( "(" );
    n.e1.accept(this);
    System.out.print( " * " );
    n.e2.accept(this);
    System.out.print( ")" );
  }

  // Exp e1,e2;
  public void visit(ArrayLookup n) {
    System.out.print( "(" ); 
    n.e1.accept(this);
    System.out.print( "[" );
    n.e2.accept(this);
    System.out.print( "]" );
    System.out.print( ")" );
  }

  // Exp e;
  public void visit(ArrayLength n) {
    n.e.accept(this);
    System.out.print(".length");
  }

  // Exp e;
  // Identifier i;
  // ExpList el;
  public void visit(Call n) {
    n.e.accept(this);
    System.out.print(" call ");
    n.i.accept(this);
    
    // print arguments as a tab-ed column
    for ( int i = 0; i < n.el.size(); i++ ) {
      System.out.print( " " );   
      n.el.get(i).accept(this);
    }
  }

  // int i;
  public void visit(IntegerLiteral n) {
    System.out.print(n.i);
  }

  public void visit(True n) {
    System.out.print( "true" );
  }

  public void visit(False n) {
    System.out.print( "false" );
  }

  // String s;
  public void visit(IdentifierExp n) {
    System.out.print(n.s);
  }

  public void visit(This n) {
    System.out.print( "this" );
  }

  // Exp e;
  public void visit(NewArray n) {
    System.out.print( "New Int Array [ " );
    n.e.accept(this);
    System.out.print( " ]" );
  }

  // Identifier i;
  public void visit(NewObject n) {
    System.out.print( "New Object " );
    System.out.print(n.i.s);
  }

  // Exp e;
  public void visit(Not n) {
    System.out.print("!");
    n.e.accept(this);
  }

  // String s;
  public void visit(Identifier n) {
    System.out.print(n.s);
  }

  public void visit(DoubleLiteral n) {
      System.out.print( n.x );
  }

  public void visit(DoubleType n) {
      System.out.print( "double" );
  }
}
