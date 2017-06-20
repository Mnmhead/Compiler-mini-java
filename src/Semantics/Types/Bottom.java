// Copyright (c) Gyorgy Wyatt Muntean, Justin Adsuara 2017

package Semantics;

/**
 * This class represents the bottom-most type in MiniJava.
 *
 * This type is compatible with all othee types. It is to be used
 * during type checking as a placeholder for unrecognized types.
 * Similarily, during type checking, if we encounter a unrecognized variable
 * declaration, we can assign it to be 'Bottom'.
 */
public class Bottom extends MJType {
    private static Bottom bottomInstance = new Bottom();

    private Bottom() { }

    public static Bottom getInstance() {
        return bottomInstance;
    }

    public boolean equals( Object o ) {
        return o == this;
    }

    public String toString() {
        return "<unknown>";
    }
}
