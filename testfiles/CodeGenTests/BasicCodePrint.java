class Main {
    public static void main(String[] args) {
        System.out.println((new A()).m());
    }
}

class A {
    public int m() {
        System.out.println(3);
        return 5;
    }
}
