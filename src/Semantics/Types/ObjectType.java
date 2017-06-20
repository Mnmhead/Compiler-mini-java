// Copyright (c) Gyorgy Wyatt Muntean, Justin Adsuara 2017

package Semantics;

/**
 * This class represents the super class type 'Object' in MiniJava.
 *
 * Object is a singleton class.
 */
public class ObjectType extends ClassType {
   private static ObjectType objInstance = new ObjectType();

   private ObjectType() { 
      super( "Object" );
   }

   public static ObjectType getInstance() {
      return objInstance;
   }

   public boolean equals( Object o ) {
      return o == this;
   }
}
