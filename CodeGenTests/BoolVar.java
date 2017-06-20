class Main {
    public static void main(String[] args) {
        System.out.println((new A()).m());
    }
}

class A {
    public int m() {
        boolean b;
        b = 3 < 2;
        return 1;
    }
}
