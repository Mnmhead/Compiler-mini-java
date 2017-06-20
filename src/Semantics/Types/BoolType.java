package Semantics;

public class BoolType extends MJType {
    private static BoolType booleanInstance = new BoolType();

    private BoolType() { }

    public static BoolType getInstance() {
        return booleanInstance;
    }

    public boolean equals(Object o) {
        return o == this;
    }

    public String toString() {
        return "<boolean>";
    }
}
