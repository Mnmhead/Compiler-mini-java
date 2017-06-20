// Copyright (c) Gyorgy Wyatt Muntean, Justin Adsuara 2017

package Semantics;

import java.util.*;

/**
 * This class represents a Method's Symbol Table in MiniJava.
 */
public class MethodSymbolTable extends SymbolTable {
   
   private Map<String,MJType> varDecls; // maps declared variable names to their declared types 
   private ClassSymbolTable parentTable;  // reference to this Method's parent scope

   /*
    * Constructs a MethodSymbolTable with the passed in Class Symbol Table.
    *
    * @param parentTable, the ClassSymbolTable this method lives in
    * @requires cst != null
    */
   public MethodSymbolTable( ClassSymbolTable parentTable ) {
      varDecls = new HashMap<String,MJType>();
      this.parentTable = parentTable;
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
    * Gets the type of the variable with name 'name'. If the var 'name' does not exist
    * in this scope, we check the parent scope for the variable.
    *
    * @returns null if a variable with name 'name' does not exist,
    *          otherwise we return the Type of var 'name'
    */
   public MJType getVarType( String name ) {
      MJType t = varDecls.get( name );
      if( t == null ) {
         // this scope didn't contain the variable decl, check the parent scope.
         return parentTable.getVarType( name );
      } else {
         return t;
      }
   }

   /*
    * Gets the ClassSymbolTable in which this MethodSymbolTable lives in.
    *
    * @returns this method table's parent table
    */
   public ClassSymbolTable getParentTable() {
      return parentTable;
   }

   /*
    * Prints the Symbol Table.
    */   
   public void printTable( String method_name ) {
      Set<String> vars = varDecls.keySet();
      Iterator<String> var_it =  vars.iterator();
      System.out.println( method_name + "\'s" + " method table: " );
      while( var_it.hasNext() ) { 
         String name = var_it.next(); 
         System.out.print( "     " );
         System.out.println( "Name: " + name + ", type: " + varDecls.get( name ) );  
      }   
      System.out.println( "" );
   }
}
