class Main {
    public static void main(String[] args) {
        System.out.println(99);
    }
}

class A {
    public A f() {
        return new A();
    }    
}
