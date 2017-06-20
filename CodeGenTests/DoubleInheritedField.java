class Main {
    public static void main(String[] args) {
        System.out.println(new Client().m());
    }
}

class Client {
    public int m() {
        A a;
        double res;
        B b;
        
        a = new A();
        res = a.setX(0.0);
        res = a.printX();
        res = a.setX(1.0);
        res = a.printX();
        b = new B();
        res = b.print();
        return 0;
    }
}

class A {
    int i;
    double x;
    double z;
    boolean b;
    
    public double setX(double xNew) {
        x = xNew;
        return x;
    }

    public double printX() {
        System.out.println(x);
        return x;
    }
}

class B extends A {
    double x;

    public double print() {
        x = 2.0;
        System.out.println(x);
        z = 3.0;
        System.out.println(z);
        i = 4;
        System.out.println(4);
        i = 5;
        System.out.println(5);
        return 0.0;
    }
}
