package Semantics;

/**
 * This class represents the base type 'int' in MiniJava. 
 * 
 * Int is a singleton class.
 */
public class DoubleMJType extends MJType {
    private static DoubleMJType doubleInstance = new DoubleMJType();

    private DoubleMJType() { }

    public static DoubleMJType getInstance() {
        return doubleInstance;
    }

    public boolean equals(Object o) {
        return o == this;
    }

    public String toString() {
        return "<double>";
    }
}
