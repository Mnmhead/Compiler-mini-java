// Copyright (c) Gyorgy Wyatt Muntean, Justin Adsuara 2017

package Semantics;

/**
 * This class represents the Class type in MiniJava.
 */
public class ClassType extends MJType {
    public String className;  // name of this MiniJava class type
   
    /*
     * Constructs a new Class with a specified name.
     * By default this new Class's parentType and baseType are the MiniJava type 'Object'.
     */
    public ClassType( String name ) { 
       this.className = name;
    }

    /*
     * Standard hashCode operation.
     */
    public int hashCode() {
        return className.hashCode();
    }

    /*
     * Standard equality operation. Two ClassTypes are equal if they share the same name.
     */
    public boolean equals( Object o ) {
        if( o instanceof ClassType ) {
            ClassType c = (ClassType) o;
            if( c.className.equals( this.className ) ) {
                // comparing the name of classes should be enough
                return true;
            }
        } 
       
        return false;
    }
    public String toString() {
        return "<class " + className + ">";
    }

}
