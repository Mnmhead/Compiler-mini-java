// Copyright (c) Gyorgy Wyatt Muntean, Justin Adsuara 2017

package AST.Visitor;

import AST.*;
import Semantics.*;

/**
 * A visitor used for filling out symbol tables for our AST.
 */
public class SymbolTableVisitor implements Visitor {
   public GlobalSymbolTable gst = GlobalSymbolTable.getInstance();
   private boolean print_enabled;
   private int num_errors;
   private boolean fatal_class_loop;
   private boolean fatal_undeclared_class;

   /*
    * Contructs a SymbolTableVisitor.
    */
   public SymbolTableVisitor() {
      this.gst = GlobalSymbolTable.getInstance();
      this.print_enabled = false;
      this.num_errors = 0;
      this.fatal_class_loop = false;
      this.fatal_undeclared_class = false;
   }

   /*
    * Gets the total number of errors found during the pass.
    *
    * @returns number of errors
    */
   public int errorCount() {
      return num_errors;
   } 
  
   /*
    * Enable printing for Symbol Table information.
    */
   public void enablePrint() {
      print_enabled = true;
   }

   /*
    * Returns if a fatal inheritance loop was found or not.
    *
    * @returns true when a fatal loop was encountered,
    *          false otherwise
    */
   public boolean checkFatalLoop() {
      return fatal_class_loop;
   }

   /*
    * Returns true if a fatal undeclared class error was encountered.
    */
   public boolean checkUndeclaredClass() {
      return fatal_undeclared_class;
   }

  // MainClass m;
  // ClassDeclList cl;
  public void visit(Program n) {
    n.m.accept( this );

    // Accept the ClassDecls
    for( int i = 0; i < n.cl.size(); i++ ) {
      n.cl.get(i).accept(this);
    }

    // check that all classes in the class tree were declared
    fatal_undeclared_class = !gst.classTree.validateClassDeclaration();

    // check loop property, if the check returns false there was a fatal cycle
    fatal_class_loop = !gst.classTree.checkTreeCycles();
  }
  
  // Identifier i1,i2;
  // Statement s;
  public void visit(MainClass n) {
      ClassType type = new ClassType( n.i1.s );
      ClassSymbolTable cst = new ClassSymbolTable( type ); 
      if( !gst.addClassST( cst ) ) { 
         // Ok so there is no way this would actually happen, but I included the error
         // check anyways.
         handleDupDecl( "class", type.className, n.line_number );
         return;
      }   

      // optional printing
      if( print_enabled ) {
         cst.printTable();
      }
  }

  // Identifier i;
  // VarDeclList vl;
  // MethodDeclList ml;
  public void visit(ClassDeclSimple n) {
    // 1. Create a new ClassType for this declaration
    // 2. Create a ClassSymbolTable for this declaration
    // 3. Add the ClassSymbolTable to the GlobalSymbolTable 
    //      (if one already exists, accumulate an error and move 
    //       past this declaration like it never happened)
    // 4. Add the ClassSymbolTable to this ASTnode
    // 5. Create a new ClassTreeNode and add it to the ClassTree 
    // 6. Add variable and add methods
    String name = n.i.s;
    ClassType type = new ClassType( name );
    ClassSymbolTable cst = new ClassSymbolTable( type );
    
    if( !gst.addClassST( cst ) ) {
       // failed to add class table because it already existed
       handleDupDecl( "class", type.className, n.line_number );
       return;
    } 

    n.setClassSymbolTable( cst );
    ClassTreeNode node = new ClassTreeNode( type );
    gst.classTree.addNode( node );
    
    // Add all var decls to this class by calling our wrapper function
    addVarDecls( cst, n.vl );
    // Add all the method decls to this class by caling our wrapper function
    addMethodDecls( cst, n.ml );
   
    // optional printing
    if( print_enabled ) {
       printHelper( cst, n.ml );
    }
}
 
  // Identifier i;
  // Identifier j;
  // VarDeclList vl;
  // MethodDeclList ml;
  public void visit(ClassDeclExtends n) {
    // 1. Create a new ClassType for this declaration
    // 2. Create a ClassSymbolTable for this declaration
    // 3. Add the ClassSymbolTable to the GlobalSymbolTable 
    //      (if one already exists, accumulate an error and move 
    //       past this declaration like it never happened)
    // 4. Add the ClassSymbolTable to this ASTnode
    // 5. Create a new ClassTreeNode for this class and the super class, 
    //    and then add them to the class tree
    String name = n.i.s;
    ClassType type = new ClassType( name );
    ClassSymbolTable cst = new ClassSymbolTable( type );
    
    if( !gst.addClassST( cst ) ) {
       // failed to add class table because it already existed
       handleDupDecl( "class", type.className, n.line_number );
       return;
    } 

    n.setClassSymbolTable( cst );

    ClassTreeNode childNode = new ClassTreeNode( type );
    ClassTreeNode parentNode = new ClassTreeNode( new ClassType( n.j.s ) );
    gst.classTree.addNode( childNode, parentNode );

    // Add all the variable decls to this class by calling our wrapper function
    addVarDecls( cst, n.vl );
    // Add all the method decls to this class by caling our wrapper function
    addMethodDecls( cst, n.ml );
 
    // optional printing
    if( print_enabled ) {
       printHelper( cst, n.ml );
    }
  }

  // Type t;
  // Identifier i;
  // FormalList fl;
  // VarDeclList vl;
  // StatementList sl;
  // Exp e;
  public void visit(MethodDecl n) {
    // 1. Add each argument to the SymbolTable
    // 2. Add each varDecl to the SymbolTable
    // 3. handle any double declaration errors (ignore second decl and move on)     

    MethodSymbolTable mst = n.mst;

    // First, add all arguments to this symbol table
    for( int i = 0; i < n.fl.size(); i++ ) {
      Formal f = n.fl.get( i );
      if( !addFormal( mst, f ) ) {
         // failed to add this argument to the SymbolTable because some argument with 
         // the same name already was listed in this method decl.
         // Handle the error, skip this arg decl
         handleDupDecl( "argument", f.i.s, f.line_number );
         continue;
      }
    }
   
    // Add all the variable decls to this class by calling our wrapper function
    addVarDecls( mst, n.vl );
  }

  /*
   * Helper function to add a list of variable declarations to a SymbolTable.
   * Error handling is packaged up inside.
   */
  private void addVarDecls( SymbolTable st, VarDeclList vdl ) {
      for( int i = 0; i < vdl.size(); i++ ) {
         VarDecl v = vdl.get( i );
         if( !addVarDecl( st, v ) ) {
           // failed to add this variable because it was previously declared in this
           // scope. Handle the error, and skip this decl
           handleDupDecl( "variable", v.i.s, v.line_number );
           continue;
         }
       }
   }

  /*
   * Helper function to add a list of variable declarations to a SymbolTable.
   * Error handling is packaged up inside.
   */
  private void addMethodDecls( ClassSymbolTable cst, MethodDeclList mdl ) {
      for ( int i = 0; i < mdl.size(); i++ ) {
        MethodDecl m = mdl.get( i );
        if( !addMethodDecl( cst, m ) ) {
           // failed to add method decl because an identical one already existed
           // handle the error, then skip this decl
           handleDupDecl( "method", m.i.s, m.line_number );
           continue;
        }
        buildMethodSymbolTable( m, cst );
        m.accept(this);
      }
  }

  /*
   * Helper/wrapper function to add varibale declarations to a ClassSymbolTable
   */
  private boolean addVarDecl( SymbolTable st, VarDecl v ) {
    // I need a way to resolve the ASTNode Type into one of our MJTypes.
    // Does this mean I have to put code into each ASTNode class? I cant see a better way...
    String var_name = v.i.s;
    MJType mj_type = v.t.getMiniJavaType();
   
    return st.addVarDecl( var_name, mj_type ); 
  }

   /*
    * Helper/wrapper function to add method declarations to ClassSymbolTables
    */
   private boolean addMethodDecl( ClassSymbolTable cst, MethodDecl m ) {
      String method_name = m.i.s;
      MJMethod method_type = m.getMiniJavaType();

      return cst.addMethodDecl( method_name, method_type );
   }

   /*
    * Hands a methodDecl node this ClassSymbolTable so it can build itself a reference
    * to the ClassSymbolTable within its own MethodSymbolTable.
    */
   private void buildMethodSymbolTable( MethodDecl m, ClassSymbolTable cst ) {
      MethodSymbolTable mst = new MethodSymbolTable( cst );
      m.setMethodSymbolTable( mst );
   }

   /*
    * Helper function to handle duplicate declarations. 
    * Takes in a string describing the type of declaration, i.e. "class", "method", "variable"
    * Takes in the name of the declaration and the line number.
    */
   private void handleDupDecl( String declTypeStr, String name, int line_number ) {
      // failed to add class table because it already existed
      System.err.println( "Duplicate declaration of " + declTypeStr + ": " + name 
                           + " at line " + line_number );
      num_errors += 1;
   }

   /*
    * Helper function to add arguments to MethodSymbolTables.
    */
   private boolean addFormal( MethodSymbolTable mst, Formal f ) {
      String arg_name = f.i.s;
      MJType arg_type = f.t.getMiniJavaType(); 

      return mst.addVarDecl( arg_name, arg_type );
   }

   /*
    * Print helper to print out symbol tables.
    */ 
   private void printHelper( ClassSymbolTable cst, MethodDeclList ml ) {
      cst.printTable();
      System.out.println( "Method Symbol Tables for Class: " + cst.type.className );
      for( int i = 0; i < ml.size(); i++ ) {
          MethodDecl m = ml.get( i );
          MethodSymbolTable mst = m.mst;
          if( mst != null ) {
            mst.printTable( m.i.s );
          }
      }
   }

  // No need to visit the nodes below.
  public void visit(Formal n) { }
  public void visit(VarDecl n) { }
  public void visit(IntArrayType n) { }
  public void visit(DoubleLiteral n) { }
  public void visit(DoubleType n) { }
  public void visit(BooleanType n) { }
  public void visit(IntegerType n) { }
  public void visit(IdentifierType n) { }
  public void visit(Block n) { }
  public void visit(If n) { }
  public void visit(While n) { }
  public void visit(Print n) { }
  public void visit(Assign n) { }
  public void visit(ArrayAssign n) { }
  public void visit(And n) { }
  public void visit(LessThan n) { }
  public void visit(Plus n) { }
  public void visit(Minus n) { }
  public void visit(Times n) { }
  public void visit(ArrayLookup n) { }
  public void visit(ArrayLength n) { }
  public void visit(Call n) { }
  public void visit(IntegerLiteral n) { }
  public void visit(True n) { }
  public void visit(False n) { }
  public void visit(IdentifierExp n) { }
  public void visit(This n) { }
  public void visit(NewArray n) { }
  public void visit(NewObject n) { }
  public void visit(Not n) { }
  public void visit(Identifier n) { }
}
