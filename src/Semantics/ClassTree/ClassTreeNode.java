// Copyright (c) Gyorgy Wyatt Muntean, Justin Adsuara 2017

package Semantics;

import java.util.*;

/**
 * This class represents a Node in the ClassTree.
 */
public class ClassTreeNode {

   private ClassTreeNode parent;  // pointer to this node's parent
   private Set<ClassTreeNode> children;  // collection of this node's children
   public ClassType type;  // MiniJava type associated with this node

   /*
    * Contructs a ClassTreeNode with passed in ClassType.
    */
   public ClassTreeNode( ClassType type ) {
      this.children = new HashSet<ClassTreeNode>();
      this.parent = null;  // filled in dynamically during Symbol table pass
      this.type = type;
   }

   /*
    * @returns class name of this node.
    */
   public String getName() {
      return type.className;
   }

   /*
    * Sets this node's parent to p.
    */
   public void setParent( ClassTreeNode p ) {
      this.parent = p;
      parent.addChild( this );
   }

   /*
    * Gets this node's parent.
    */
   public ClassTreeNode getParent() {
      return parent;
   }   

   /*
    * Gets the set of children of this node.
    */
   public Set<ClassTreeNode> getChildren() {
      return children;
   }

   /*
    * Adds node as a child of this ClassTreeNode.
    *
    * @returns false if this node already exists as a child,
    *          true otherwise
    */
   public boolean addChild( ClassTreeNode node ) {
      return children.add( node );
   }

   /*
    * Standard equality override.
    * Two nodes are equal if they have the same type.
    */  
   public boolean equals( Object o ) {
      if( o instanceof ClassTreeNode ) {
         ClassTreeNode ctnode = (ClassTreeNode) o;
         // two ClassTreeNodes are equal if they hold the same type
         return ctnode.type.equals( this.type );
      }

      return false;
   }
}
