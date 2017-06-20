class A {
    public static void main(String[] args) {
        {
            if (3.0 < 5.0) {
               System.out.println(0.0);
            } else {
                System.out.println(1.0);
            }
            if (5.0 < 3.0) {
                System.out.println(3.0);
            } else {
                System.out.println(4.20);
            }

        }
    }
}

class B {
    public double m() {
        return 2.0;
    }

    public double n() {
        return 2.0e10;
    }

    public double o() {
        return 3.;
    }
}
