class Main {
    public static void main(String[] args) {
        System.out.println(99);
    }
}

class A {
    public int f() {
        int i;

        i= 5 + 6;
        i = i - 7;
        i = 8 * i;
        return i;
    }

    public boolean less(int x, int y) {
        boolean b;

        b = x < y;
        b = b && true;
        return !b;
    }

    public int len(int[] l) {
        return l.length.length;
    }

}

class B {
    int i;
}
