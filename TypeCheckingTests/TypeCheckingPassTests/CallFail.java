class Main {
    public static void main(String[] args) {
        System.out.println(99);
    }
}

class A {
    public A f(A a, G g) {
        return this;
    }
}

class B extends A {
    public int g() {
        A a;
        a = a.f(new G(), new G());
        return 5;
    }
}

class G {

}


