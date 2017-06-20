// Copyright (c) Gyorgy Wyatt Muntean, Justin Adsuara 2017

package Semantics;

import java.util.*;

/**
 * This class represents the Global Symbol table for a MiniJava Program.
 * This class is a singleton class.
 */
public class GlobalSymbolTable extends SymbolTable {

   private static GlobalSymbolTable gst = new GlobalSymbolTable();  // single instance of this class
   private Map<ClassType, ClassSymbolTable> classes;  // maps class name Strings to their Class STs
   public ClassTree classTree;  // The tree to represent the class inheritance structure

   /*
    * Constructs a GloablSymbolTable instance.
    */
   private GlobalSymbolTable() { 
      classes = new HashMap<ClassType, ClassSymbolTable>();
      classTree = new ClassTree();
   }

   /*
    * @returns an instance of this singleton GlobalSymbolTable.
    */ 
   public static GlobalSymbolTable getInstance() {
      return gst;
   }

   /*
    * Method stub from abstract class. Unused.
    */
   public boolean addVarDecl( String name, MJType type ) {
      // Global Variables not supported in MiniJava
      return false;
   }

   /*
    * Adds a class symbol table entry to the GST.
    *
    * @returns true if this class name did not exist,
    *          false if name is already an entry in the GST.
    */
   public boolean addClassST( ClassSymbolTable cst ) {
      if( cst == null ) {
         return false;
      }

      ClassType c = cst.type;
      if( classes.containsKey( c ) ) {
         return false;
      }

      classes.put( c, cst );
      return true;
   }

   /*
    * Gets the ClassSymbolTable associated with the class 'name'.
    *
    * @returns null if a class with this name does not exist,
    *          otherwise return the CST associated with the class 'name'.
    */
   public ClassSymbolTable getClassST( ClassType c ) {
      return classes.get( c );
   }

   //-----------------DEBUGGING METHODS--------------------------
   public void printClasses() {
      Set<ClassType> cts = classes.keySet();
      Iterator<ClassType> i = cts.iterator();
      System.out.println( "gst has..." );
      while( i.hasNext() ) {
         System.out.print( i.next().className + " " ); 
      }
      System.out.println();
      System.out.println();
   }

   public void printClassTables() {
      Set<ClassType> cts = classes.keySet();
      Iterator<ClassType> i = cts.iterator();
      System.out.println( "Printing all class tables:" );
      while( i.hasNext() ) {
         ClassSymbolTable cst = classes.get( i.next() );
         cst.printTable();
         System.out.println();
      }
      System.out.println(); 
   }
}
