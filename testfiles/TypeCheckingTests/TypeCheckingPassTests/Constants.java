class Main {
    public static void main(String[] args) {
        System.out.println(99);
    }
}

class A {
    public int f() {
        return 5;
    }

    public boolean g() {
        return true;
    }
    
    public boolean h() {
        return false;
    }

    public A i() {
        return new A();
    }

    public A j() {
        return this;
    }
    
    public int[] k() {
        return new int[3];
    }
}

class B {
    int i;
}
