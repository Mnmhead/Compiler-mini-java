// Copyright (c) Gyorgy Wyatt Muntean, Justin Adsuara 2017

package Semantics;

import java.util.*;

/**
 * This class represents a SymbolTable in the domain of a MiniJava Class.
 */
public class ClassSymbolTable extends SymbolTable {

   private Map<String,MJType> varDecls;  // Maps declared variable names to their declared MiniJava type
   private Map<String,MJMethod> methodDecls;  // Maps declared method names to their associated 'method type'
   
   public ClassType type;  // This is the class type the symbol table exists for   
   public GlobalSymbolTable gst;  // pointer to the global symbol table

   /*
    * Constructs a new ClassSymbolTable.
    */
   public ClassSymbolTable( ClassType ct ) {
      this.type = ct;
      this.gst = GlobalSymbolTable.getInstance();
      varDecls = new HashMap<String,MJType>();
      methodDecls = new HashMap<String,MJMethod>();
   }

   /*
    * Adds a new variable declaration to this Class Symbol Table.
    * NOTE: We do not allow 'shadowing' in MiniJava.
    *
    * @requires name != null
    *           t != null
    * @returns true if this variable name has not been used before in the current scope.
    * @returns false if this variable name already exists in the current scope
    */
   public boolean addVarDecl( String name, MJType type ) {
      if( varDecls.containsKey( name ) ) {
         return false;
      }

      varDecls.put( name, type ); 
      return true;
   }

   /*
    * Adds a new method declaration to this Class Symbol Table.
    * NOTE: We do not allow method overloading in MiniJava.
    *
    * @requires name != null
    *            mtype != null
    * @returns true if this method name has not been used before in the current scope.
    * @returns false if this method name already exists in the current scope.
    */
   public boolean addMethodDecl( String name, MJMethod mtype ) {
      if( methodDecls.containsKey( name ) ) {
         return false;
      }

      methodDecls.put( name, mtype );
      return true;
   }

   /*
    * Gets the type of the variable with name 'name'.
    *
    * @returns null if a variable with name 'name' does not exist,
    *          otherwise we return the Type of var 'name'
    */
   public MJType getVarType( String name ) {
      MJType t = varDecls.get( name );
      if( t == null ) {
         // if the variable didn't exist in this classes declarations,
         // we check the parent classes

         // Get the parent ClassSymbolTable
         ClassTree ct = gst.classTree;
         ClassSymbolTable nextTable = ct.getParentClassST( type );  
         if( nextTable == null ) {
            // var does not exist
            return null;
         }

         // Try the next class table in the heirarchy
         return nextTable.getVarType( name );          
      }

      return t;
   }

   /*
    * Gets the type of the method with name 'name'.
    *
    * @returns null if a method with name 'name' does not exist,
    *          otherwise we return the Type of the method 'name'
    */
   public MJMethod getMethodType( String name ) {
      MJMethod m = methodDecls.get( name );
      if( m == null ) {
         // if the method didn't exist in this classes declarations,
         // we check the parent classes

         // Get parent from the ClassTree
         ClassTree ct = gst.classTree;
         ClassSymbolTable nextTable = ct.getParentClassST( type );
         if( nextTable == null ) {
            // method does not exist
            return null;
         }

         // Try the next ClassSymbolTable in the heirarchy
         return nextTable.getMethodType( name );
      }

      return m;
   }

   /*
    * Takes in a method name and returns a class name. The class name will be the
    * name of the class that the method was last declared in (using the class tree hierarchy).
    *
    * @requires This method assumes that type checking has been completed and that there is
    * a valid class hierarchy!
    *
    * @returns The name of the class in which methodName was declared (most recently in the hierarchy).
    */
   public String getSourceOfInheritance( String methodName ) {
      MJMethod m = methodDecls.get( methodName );
      String className = type.className;
      
      if( m == null ) {
         // the method did not exist in this class, find the nearest parent
         // class that did contain the method.

         ClassTree ct = gst.classTree;
         ClassSymbolTable nextTable = ct.getParentClassST( type );
         // We assume that the nextTable must exist!
         return nextTable.getSourceOfInheritance( methodName );
      } else {
         return className;
      }
   }

   /*
    * Gets a set of all variables declared in this class.
    *
    * @returns a set of strings containing all var names declared in this class.
    */
   public Set<String> getVarNames() {
      return varDecls.keySet();
   }

   /*
    * Gets a set of all methods declared in this class.
    *
    * @returns a set of strings containing all method names declared in this class.
    */
   public Set<String> getMethodNames() {
      return methodDecls.keySet();
   }

   /*
    * Print this Symbol Table.
    */ 
   public void printTable() {
      Set<String> vars = varDecls.keySet();
      Iterator<String> var_it =  vars.iterator();
      System.out.println( "Class Symbol Table: " + type.className ); 
      System.out.println( "   Vars: " );
      while( var_it.hasNext() ) {
         String name = var_it.next(); 
         System.out.print( "     " );
         System.out.println( "name: " + name + ", type: " + varDecls.get( name ) ); 
      } 

      Set<String> meths = methodDecls.keySet();
      Iterator<String> meth_it = meths.iterator();
      System.out.println( "   Methods: " );
      while( meth_it.hasNext() ) {
         String name = meth_it.next();
         System.out.print( "     " );
         System.out.println( "name: " + name );
         System.out.println( "        " + methodDecls.get( name ) );
         System.out.println();
      } 
   }

}
