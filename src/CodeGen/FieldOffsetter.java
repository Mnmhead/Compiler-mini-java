// Copyright (c) Gyorgy WYatt Muntean, Justin Adsuara 2017

package CodeGen;

import Semantics.*;
import java.util.*;

/**
 * This class stores the memory offsets of object's fields.
 *
 * This is a singleton class.
 */
public class FieldOffsetter extends Offsetter {
   private static FieldOffsetter offsetter = new FieldOffsetter();
   private GlobalSymbolTable gst;
   private ClassTree ct;
      
   // Maps class-names to thier object layout maps.
   // An object layout map is simply a Map from variable names to integer values.
   // The integer values represent the index at which the variable exists at within 
   // the objects memory layout. For example, if variable 'x' mapped to value 6, then x 
   // is the 6th field within the object's memory layout. Since all fields are 8-byte fields,
   // we can easily find the address of 'x' given the addess of the object.
   private Map<String,Map<String,Integer>> objects; 
   
   // Maps objects by name to thier size measured in number of 64-bit fields.
   private Map<String,Integer> objectSizeMap;  
   
   /*
    * Constructs a FieldOffsetter object.
    */  
   private FieldOffsetter() { 
      this.objects = new HashMap<String,Map<String,Integer>>();
      this.objectSizeMap = new HashMap<String,Integer>();
      this.gst = GlobalSymbolTable.getInstance();
      this.ct = gst.classTree;
      buildOffsetter();
   }

   /*
    * Gets an instance of this singleton class.
    *
    * @returns the singleton instance of this class.
    */
   public static FieldOffsetter getInstance() {
      return offsetter;
   }

   /*
    * Gets the size in 8-byte fields of an object.
    *
    * @returns the number of 8-byte instance variables in the passed in object.
    */
   public int getObjectSize( String className ) {
      return objectSizeMap.get( className ).intValue();
   }

   /*
    * Takes in a class name and a variable name. Determines the offset (i.e. the position) 
    * of the variable within the object specified by 'className'.
    * NOTE: WE REALLY BANK ON THE FACT THAT className AND varName EXIST!
    * 
    * @returns the offset of the variable, varName, within the object, className.
    */
   public int getOffset( String className, String varName ) {
      Map<String,Integer> offsetMap = objects.get( className );
      if( offsetMap == null ) {
         System.err.println( "class name did not exists in getOffset." );
      }
      
      if( !offsetMap.containsKey( varName ) ) {      
         // The variable did not exist within this classes immediate
         // fields. We now search the parent classes fields.
         ClassTreeNode node = ct.getNode( new ClassType( className ) );
         ClassTreeNode parent = node.getParent();

         if( ct.isRoot( parent ) ) {
            System.err.println( "var name did not reference a valid variable in getOffset." );
         }

         return getOffset( parent.type.className, varName );
      } else {
         Integer offset = offsetMap.get( varName );
         return offset.intValue();
      }
   }

   /*
    * Visit the class nodes in a BFS manner, adding each class's object layout to
    * the FieldOffsetter.
    */
   private void buildOffsetter() {
      LinkedList<ClassTreeNode> class_q = new LinkedList<ClassTreeNode>();
      ClassTreeNode root = ct.getRoot();

      // add all the direct children of the root to the queue
      Set<ClassTreeNode> simpleClasses = root.getChildren();
      Iterator<ClassTreeNode> n_it = simpleClasses.iterator(); 
      while( n_it.hasNext() ) {
         ClassTreeNode node = n_it.next();
         class_q.add( node );
      }

      // while the queue is not empty:
      // 1. pop a class node from the queue
      // 2. get its parents offset (if it has a parent)
      // 3. call helper function to add this class's object offsets 
      // 4. add this class's children to the queue
      while( !class_q.isEmpty() ) {
         ClassTreeNode node = class_q.remove();
         String className = node.type.className;
        
         // get the number of fields in the parent, if a parent exists
         int parentOffset = 0;
         ClassTreeNode parentNode = node.getParent();
         if( !ct.isRoot( parentNode ) ) {
            String parentClassName = parentNode.type.className;
            parentOffset = objectSizeMap.get( parentClassName ).intValue();
         }
   
         // add this class's object offset info
         addObject( className, parentOffset ); 
      
         // add children to the queue 
         Set<ClassTreeNode> children = node.getChildren();
         Iterator<ClassTreeNode> child_it = children.iterator();
         while( child_it.hasNext() ) {
            ClassTreeNode child = child_it.next();
            class_q.add( child );
         }
      }
   }

   /*
    * Helper function to add the passed in object's offset information to the FieldOffsetter.
    * Additionally, we add the passed in object's size (in number of 64-bit fields) to
    * the objectSizeMap. 
    */
   private void addObject( String className, int parentOffset ) {
      // create a map to store this Object's field offsets,
      // an offset is simply stored as an integer where the integer represents
      // the 'index' of the field within the object.
      HashMap<String,Integer> varOffsetMap = new HashMap<String,Integer>();    
      ClassSymbolTable cst = gst.getClassST( new ClassType( className ) );
      if( cst == null ) {
         System.err.println( "whoaaaa...." + className + "\'s symbol table did not exist." );
         return;
      }
      
      // get the field names from the ClassSymbolTable
      Set<String> vars = cst.getVarNames();
      Iterator<String> v_it = vars.iterator(); 
      
      int field_count = 0;  // counts how many fields we encountered in this object
      int next_offset = parentOffset + 1;  // keeps track of the offset of the next field
      // compute field offsets for this object
      while( v_it.hasNext() ) {
         String var = v_it.next();
         varOffsetMap.put( var, new Integer( next_offset ) );
         field_count += 1;
         next_offset += 1;
      }

      // add this object to the objectSizeMap
      int thisObjectSize = parentOffset + field_count;
      objectSizeMap.put( className, new Integer( thisObjectSize ) );

      // add this object's offset map to the objects->layout map
      objects.put( className, varOffsetMap );
   }

   //---------------------DEBUGGING PRINT------------------------
   public void printAllObjectLayouts() {
      Set<String> classes = objects.keySet();      
      Iterator<String> c_it = classes.iterator();

      while( c_it.hasNext() ) {
         String className = c_it.next();
         System.out.println( "Layout for  " + className + ":" );
         System.out.println( "   size (in # of 8-byte fields): "
                             + objectSizeMap.get( className ).intValue() );

         Map<String,Integer> offsetMap = objects.get( className );
         Set<String> vars = offsetMap.keySet(); 
         Iterator<String> v_it = vars.iterator();
         while( v_it.hasNext() ) {
            String var = v_it.next();
            int v_offset = offsetMap.get( var ).intValue();
            System.out.println( "   var: " + var + " --> " + v_offset );
         }
         System.out.println();
      }
   }
}
