class Main {
    public static void main(String[] args) {
        System.out.println(99);
    }
}

class A {
    public int f() {
        return true;
    }

    public boolean g() {
        return new A();
    }
    
    public A h() {
        return 5;
    }

    public A i() {
        return new B();
    }

    public int[] j() {
        int[] i;

        i = new int[3];

        i[true] = 5;
        i[5] = true;
        return i;
    }
}

class B {
    int i;
}
