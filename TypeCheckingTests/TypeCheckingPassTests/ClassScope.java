class Main {
    public static void main(String[] args) {
        System.out.println(5);
    }
}


class A {
    int x;
    
    public int f() {
        return x;
    }

    public int g() {
        int i;
        return this.f();
    }
}
