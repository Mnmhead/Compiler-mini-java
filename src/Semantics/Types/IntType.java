package Semantics;

/**
 * This class represents the base type 'int' in MiniJava. 
 * 
 * Int is a singleton class.
 */
public class IntType extends MJType {
    private static IntType intInstance = new IntType();

    private IntType() { }

    public static IntType getInstance() {
        return intInstance;
    }

    public boolean equals(Object o) {
        return o == this;
    }

    public String toString() {
        return "<int>";
    }
}
