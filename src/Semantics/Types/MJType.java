// Copyright (c) Gyorgy Wyatt Muntean, Justin Adsuara 2017

package Semantics;

/**
 * This abstract class represents types in MiniJava.
 */
abstract public class MJType {
    
    /* 
     * returns true if this or t is Bottom, or if this is the same type as t
     */
    public boolean matches( MJType t ) {
        Bottom b = Bottom.getInstance();
        return this.equals(t) || this.equals(b) || t.equals(b);
    }
}
