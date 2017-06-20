class Main {
    public static void main(String[] args) {
        System.out.println(new A().m(new B().getBool(),
                                     new A().getB(),
                                     1,
                                     new B().getC()));
    }
}

class A {
    public int m(boolean bool, B b, int i, C c) {
        System.out.println(99);
        return 100;
    }

    public B getB() {
        System.out.println(1);
        return new B();
    }
}

class B {
    public boolean getBool() {
        System.out.println(0);
        return false;
    }

    public C getC() {
        System.out.println(4);
        return new C();
    }
}

class C {

}
        
