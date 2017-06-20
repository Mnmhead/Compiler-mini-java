class Main {
    public static void main(String[] args) {
        System.out.println(5);
    }
}


class A {
    int x;

    public int f() {
        return 5;
    }
}

class B extends A {
    public int g() {
        return this.f() + x * 5;
    }
}
