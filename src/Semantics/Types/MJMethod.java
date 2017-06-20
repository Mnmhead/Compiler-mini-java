// Copyright (c) Gyorgy Wyatt Muntean, Justin Adsuara 2017

package Semantics;

import java.util.List;

/**
 * This class represents the type of a method in MiniJava.
 *   
 * A type checking operation isOverridable() is available on this class.
 * The kinds of information that defines a MiniJava method type are,
 * return parameter type, and argument types.
 */
public class MJMethod extends MJType {
    public List<MJType> argumentTypes;
    private int numArgs;
    public MJType returnType;

    /*
     * Constructs a new MJMethod with argument list types and return Type.
     *
     * @requires args != null
     */    
    public MJMethod( MJType returnType, List<MJType> args ) { 
      assert( args != null );
      this.argumentTypes = args;
      this.numArgs = args.size();
      this.returnType = returnType; 
    }

    // should never be called
    public boolean equals( Object o ) {
        System.err.println( "MJMethod equals() was called." );
        System.exit( 1 );
        return false;
    }

   /*
    * Determines if this method type is able to be overrided by MJMethod, m.
    * Assumes that a ClassTree has already been built and validated.
    *
    * @returns true if m is compatible with this method,
    *          false otherwise
    */
   public boolean isOverridable( MJMethod m ) {
      // check num args
      if( m.numArgs != this.numArgs ) {
         return false;
      }

      // Check that m's returnType is a subtype of this MJMethod's returnType
      if( m.returnType instanceof ClassType ) {
         if( !(this.returnType instanceof ClassType) ) {
            return false;
         }         

         // Get class inheritance tree
         GlobalSymbolTable gst = GlobalSymbolTable.getInstance();
         ClassTree ct = gst.classTree;
         ClassType thisType = (ClassType) this.returnType;
         ClassType mType = (ClassType) m.returnType;
         // Call sub-type routine
         if( !ct.isSubType( mType, thisType ) ) {
            return false;
         }
      } else {
         // The return types are not class types, just check equality
         if( !m.returnType.equals( this.returnType ) ) {
            return false;
         }  
      }    

      // check that the argument types align
      for( int i = 0; i < this.numArgs; i++ ) {
         MJType thisType = this.argumentTypes.get( i );
         MJType mType = m.argumentTypes.get( i );
         if( !thisType.equals( mType ) ) {
            return false;
         } 
      }
  
      return true;
   }

   /*
    * Standard toString. String representation displays typing info.
    */ 
   public String toString() {
      String s = "return type: " + returnType;
      s += ", args: ";
      for( int i = 0; i < numArgs; i++ ) {
         MJType t = argumentTypes.get( i );
         s += "" + t;
      }
   
      return s;
   }
}
