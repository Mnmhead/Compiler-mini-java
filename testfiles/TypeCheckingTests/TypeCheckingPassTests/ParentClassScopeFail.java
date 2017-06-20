class Main {
    public static void main(String[] args) {
        System.out.println(5);
    }
}


class A {
    int x;
}

class B extends A {
    public int g() {
        return this.f() + y * 5;
    }
}
