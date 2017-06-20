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
        a = a.f(a, new G());
        a = (new A()).f(this, new G());
        return 5;
    }
}

class G {

}


