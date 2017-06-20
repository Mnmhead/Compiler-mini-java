class A {
    public static void main(String[] args) {
        System.out.println(new B().m());
    }
}

class B {
    double x;
    
    public double m() {
        double y;
        
        x = 0.0;
        y = 1.0;
        System.out.println(x);
        System.out.println(y);
        return 2.0;
    }

    public double n() {
        return 2.0e10;
    }

    public double o() {
        return 3.;
    }
}
