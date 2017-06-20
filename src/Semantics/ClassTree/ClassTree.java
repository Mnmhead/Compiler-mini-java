// Copyright (c) Gyorgy Wyatt Muntean, Justin Adsuara 2017

package Semantics;

import java.util.*;

/**
 * This class represents an Inheritance Tree for MiniJava class types.
 *
 * This class is used to:
 *    -determine super/sub relationships
 *    -find variables or methods that exist in super classes
 */
public class ClassTree {

   // the root node of this tree
   private ClassTreeNode root; 

   // maps class type objects to thier node within the inheritance tree.
   private Map<ClassType,ClassTreeNode> nodeMap; 

   /*
    * Constructs a new ClassTreeObject.
    */ 
   public ClassTree() {
      nodeMap = new HashMap<ClassType,ClassTreeNode>();
      // grab the base type in MiniJava, set the root ClassTreeNode
      ObjectType rootType = ObjectType.getInstance();
      root = new ClassTreeNode( rootType ); 
      nodeMap.put( rootType, root );
   }

   /*
    * Determines if the passed in node is the root of this tree.
    *
    * @returns true if the passed in node is the root,
    *          false otherwise.
    */
   public boolean isRoot( ClassTreeNode node ) {
      return root.equals( node );
   }

   /*
    * Gets the root of the Tree.
    *
    * @returns the root node of this tree.
    */
   public ClassTreeNode getRoot() {
      return root;
   }

   /*
    * Adds the ClassTreeNode to the tree.
    */
   public void addNode( ClassTreeNode n ) {
      if( n == null ) {
         return;
      }
     
      ClassType t = n.type;
      if( nodeMap.containsKey( t ) ) {
         // this node already exists
         n = nodeMap.get( t );
      } else {
         // this node doesn't exist, so add it to the map
         nodeMap.put( t, n );
      }
    
      // Add the node as a child of the root 
      n.setParent( root );
   }

   /*
    * Adds the ClassTreeNode child to the Tree with the specified parent.
    */
   public void addNode( ClassTreeNode child, ClassTreeNode parent ) {
      if( child == null || parent == null ) {
         return;
      } 

      ClassType child_t = child.type;
      ClassType parent_t = parent.type;

      // grab the nodes from the Tree if they already exist 
      if( nodeMap.containsKey( child_t ) ) { 
         child = nodeMap.get( child_t );
      } else {
         // did not already exist, add it to map
         nodeMap.put( child_t, child );
      }
      if( nodeMap.containsKey( parent_t ) ) {
         parent = nodeMap.get( parent_t );
      } else {
         // did not already exist, add it to map
         nodeMap.put( parent_t, parent );
      }

      // simply set the child's parent to parent
      child.setParent( parent );
   }

   /*
    * Gets the ClassTreeNode associated with the passed in ClassType.
    *
    * @returns the node associated with the passed in type,
    *          null if the node does not exist.
    */
   public ClassTreeNode getNode( ClassType ct ) {
      return nodeMap.get( ct );
   }

   /*
    * A niche method that returns the parent ClassSymbol table of
    * the passed in MiniJava class t. 
    *
    * @returns The parent ClassSymbolTable of the passed in ClassType,
    *          returns null if there is no parent ClassSymbolTable
    */
   public ClassSymbolTable getParentClassST( ClassType t ) {
      if( !nodeMap.containsKey( t ) ) {
         return null;
      }  

      GlobalSymbolTable gst = GlobalSymbolTable.getInstance();
      ClassTreeNode node = nodeMap.get( t );
      ClassTreeNode parentNode = node.getParent();
      if( isRoot( parentNode ) ) { 
          // we have reached the root class, no parent ClassST
          return null;
      }   

      // Get the next ClassSymbolTable in the heirarchy
      ClassType parentClass = parentNode.type;
      ClassSymbolTable nextScope = gst.getClassST( parentClass ); 
      return nextScope;
   }

   /*
    * Determines if type t1 is a super-type of t2 .
    * 
    * @returns true if t1 is a super type of t2,
    *          false otherwise.
    */
   public boolean isSuperType( ClassType t1, ClassType t2 ) { 
      return isSubType( t2, t1 );
   }   

   /* 
    * Determines if type t1 is a sub-type of t2 .
    *  
    * @returns true if t1 is a sub type if t2,
    *          false otherwise.
    */
   public boolean isSubType( ClassType t1, ClassType t2 ) { 
      ClassTreeNode n1 = nodeMap.get( t1 );
      ClassTreeNode n2 = nodeMap.get( t2 );
      // if either node does not exist, return false
      if( n1 == null || n2 == null ) {
         return false;
      }
     
      // search n1's parents to check if it is a subtype of n2 
      while( n1 != null ) {
         if( n1.equals( n2 ) ) {
            return true;
         } 
      
         n1 = n1.getParent();
      }

      return false;
   }   

   /*
    * Validates the tree. This function assumes that the SymbolTable pass has
    * been completed.
    *
    * @returns true if this tree is valid,
    *          false otherwise.
    */
   public boolean checkTreeCycles() {
      int reachableNodes = 0;
      ArrayList<ClassTreeNode> worklist = new ArrayList<ClassTreeNode>();
      // Add root to worklist
      worklist.add( root );

      // The way the Tree's add methods work is that no cycle will
      // ever be connected to the root. Thus we can check the number of nodes
      // connected to the root against the number of nodes in the actual tree.
      while( !worklist.isEmpty() ) {
         ClassTreeNode n = worklist.remove( 0 );
         reachableNodes += 1;
      
         Iterator<ClassTreeNode> i = n.getChildren().iterator();
         while( i.hasNext() ) {
            worklist.add( i.next() ); 
         }
      }

      if( reachableNodes != nodeMap.size() ) {
         // If we are here, then there this 'ClassGraph' is disjoint (non-connected).
         // So this means we have a cycle as well, just a cycle that isnt connected to the root.
         return false;
      }

      // nothing is wrong with this Tree, return true
      return true;
   }

   /*
    * This function checks that each ClassType in the Tree actually has 
    * been properly declared. 
    * In order to make these checks we need access to the GST, which we
    * assume has already been filled out properly.
    *
    * @returns true if every class nin the tree has been validated,
    *          false otherwise.
    */
   public boolean validateClassDeclaration() {
      GlobalSymbolTable gst = GlobalSymbolTable.getInstance();

      Set<ClassType> classes = nodeMap.keySet();  
      Iterator<ClassType> it = classes.iterator();
      // check that each class has been properly declared in the GST
      while( it.hasNext() ) {
         ClassType ct = it.next();
         if( ct.equals( ObjectType.getInstance() ) ) {
            // skip over Object
            continue;
         }
         if( gst.getClassST( ct ) == null ) {
            System.err.println( "Class " + ct.className + " was undeclared but extended." );
            return false;
         }
      }
      
      return true;
   }
}
