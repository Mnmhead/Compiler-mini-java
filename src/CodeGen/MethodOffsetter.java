// Copyright (c) Gyorgy WYatt Muntean, Justin Adsuara 2017

package CodeGen;

import Semantics.*;
import java.util.*;

/**
 * This class stores the memory offsets of object's fields.
 *
 * This is a singleton class.
 */
public class MethodOffsetter extends Offsetter {
   private static MethodOffsetter offsetter = new MethodOffsetter();
   private GlobalSymbolTable gst;
   private ClassTree ct; 
    
   // Maps class-names to thier vtables.
   // A vtable is simply a specific ordering of methods.
   // If a class has a method which overrides a super class's method,
   // then the new method should exist at the same position (offset) as the
   // overrided method.
   // NOTE: We start method offsets at 0. Meaning the first method in a class
   //       will be at offset 0.
   private Map<String,Map<String,Integer>> objects; 

   /*
    * Constructs a instance of a MethodOffsetter.
    */
   private MethodOffsetter() {
      this.gst = GlobalSymbolTable.getInstance();
      this.ct = gst.classTree;
      this.objects = new HashMap<String,Map<String,Integer>>();
      buildMethodOffsetter();
   }

   /*
    * @returns the singleton instance of the MethodOffsetter.
    */
   public static MethodOffsetter getInstance() {
      return offsetter; 
   }

   /*
    * Takes in a class name and a method name. Determines
    * the position in the vtable of the method for the specified class.
    *
    * @returns the offset of the method, methodName, within the vtable of className.
    *          Note: the offset is really an index (it is not a byte offset or pointer offset)
    */
   public int getOffset( String className, String methodName ) {
      Map<String,Integer> offsetMap = objects.get( className );
      if( offsetMap == null ) { 
         System.err.println( "class name did not exists in getOffset (methodOffset)." );
      }   

      return offsetMap.get( methodName ).intValue(); 
   }

   /*
    * @returns an iterator over the method names for the passed in Class, className.
    */
   public Map<String,Integer> getMethodOffsetMap( String className ) {
      Map<String,Integer> offsetMap = objects.get( className );
      if( offsetMap == null ) { 
         System.err.println( "class name did not exists in getOffset (methodOffset)." );
      }   

      return offsetMap;
   }

   /*
    * Helper function to build the vtables/method offsets for all classes.
    * We visit classes in a BFS manner because subclasses will need information
    * from superclass, so every superclass must be visited before any subclass.
    */
   private void buildMethodOffsetter() {
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
      // 2. call helper function to add this class's method offsets 
      // 3. add this class's children to the queue
      while( !class_q.isEmpty() ) {
         ClassTreeNode node = class_q.remove();
         String className = node.type.className;

         // grab the parent class name
         ClassTreeNode parentNode = node.getParent();
         String parentClassName = parentNode.type.className;
         // if the parent class is the root class, set parent name to null
         if( ct.isRoot( parentNode ) ) {
            parentClassName = null;
         } 

         // add this class's method offset info
         addClass( className, parentClassName );

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
    * Helper to add a class's method info to the MethodOffsetter structure.
    */
   private void addClass( String className, String parentClassName ) {
      // Create a map to store this Object's method offsets.
      // We do this to fix the ordering of methods in vtables.
      HashMap<String,Integer> methodOffsetMap = new HashMap<String,Integer>();
      ClassSymbolTable cst = gst.getClassST( new ClassType( className ) );
      if( cst == null ) {
         System.out.println( "whooaaaa...." + className 
                     + "\'s symbol table did not exist (methodOffsetter)." );
         return;
      }     

      // We have 2 cases. 
      // 1. We need to inherit some method offsets from out parent
      // 2. We are a simple class decl, meaning we have no super classes.
      
      if( parentClassName != null ) {
         // Case 1.

         // Get the parent vtable, 
         // then copy all entries into our vtable
         Map<String,Integer> parentMethodMap = objects.get( parentClassName );
         copyMethodMap( methodOffsetMap, parentMethodMap );
        
         // Place all potentially overridable methods in a set (parentMethodNames)
         Set<String> parentMethodNames = parentMethodMap.keySet();     

         Set<String> methodNames = cst.getMethodNames();
         Iterator<String> m_it = methodNames.iterator();  

         // start the child class's offsets directly after its parent's offset.
         int offset = parentMethodNames.size();
         while( m_it.hasNext() ) {
            String m = m_it.next();
            if( !parentMethodNames.contains( m ) ) {
               // This method was newly declared in the child, and was never 
               // declared in the parent.
               // Append it at the end of the vtable.
               methodOffsetMap.put( m, new Integer( offset ) );
               offset += 1; 
            }
         }
 
      } else {
         // Case 2.

         // Get the method names from the ClassSymbolTable
         Set<String> methodNames = cst.getMethodNames();
         Iterator<String> m_it = methodNames.iterator();

         // Fill the offset table, ez pz
         int offset = 0;
         while( m_it.hasNext() ) {
            String m = m_it.next();
            methodOffsetMap.put( m, new Integer( offset ) );
            offset += 1;
         }
 
      } 

      // lastly, place the vtable in the overall methodOffset structure. 
      objects.put( className, methodOffsetMap ); 
   }

   /*
    * Copies all entires of the parentMap into the childMap.
    */
   private void copyMethodMap( Map<String,Integer> childMap, Map<String,Integer> parentMap ) {
      // Copy all <key, value> pairs into child
      Iterator<String> s_it = parentMap.keySet().iterator();
      while( s_it.hasNext() ) {
         String parentMethodName = s_it.next();
         Integer offset = parentMap.get( parentMethodName );
         
         childMap.put( parentMethodName, offset );
      }
   }

   //----------------------DEBUGGING PRINT-------------------
   public void printOffsets() {
      Set<String> classes = objects.keySet();
      Iterator<String> c_it = classes.iterator();

      while( c_it.hasNext() ) {
         String className = c_it.next();
         System.out.println( "Method Layout for " + className + ":" );
         
         Map<String,Integer> offsetMap = objects.get( className );
         Set<String> methods = offsetMap.keySet();
         Iterator<String> m_it = methods.iterator();
         while( m_it.hasNext() ) {
            String method = m_it.next();
            int m_offset = offsetMap.get( method ).intValue();
            System.out.println( "   method: " + method + " --> " + m_offset );
         }
      }
      System.out.println();
   }
}
