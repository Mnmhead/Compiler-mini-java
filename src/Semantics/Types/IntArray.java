package Semantics;

public class IntArray extends MJType {
    private static IntArray intArrayInstance = new IntArray();

    private IntArray() { }

    public static IntArray getInstance() {
        return intArrayInstance;
    }

    public boolean equals(Object o) {
         return o == this;
    }

    public String toString() {
        return "<int[]>";
    }
}
