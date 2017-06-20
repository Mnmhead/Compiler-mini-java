class Main {
    public static void main(String[] args) {
        {
            System.out.println((new C()).m());
            System.out.println((new C()).n());
            System.out.println((new C()).o());
        }
    }
}

class A {
    public int m() {
        System.out.println(3);
        return 5;
    }

    public int n() {
        return 6;
    }

    public int o() {
        return 7;
    }
}


class B extends A {
    public int m() {
        System.out.println(100);
        return 99;
    }
    
    public int n() {
        return 90;
    }

    public int o() {
        return 80;
    }
}

class C extends B {
    public int o() {
        return 70;
    }
}
