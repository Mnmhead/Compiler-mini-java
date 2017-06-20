class Main {
    public static void main(String[] args) {
        System.out.println(5);
    }
}


class A {
    int x;

    public int m() {
        return x + 7;
    }
}

class B extends A {

}

class C extends B {
    public int f() {
        return 5 * x - this.m();
    }
}
