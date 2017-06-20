class Main {
    public static void main(String[] args) {
        System.out.println(new A().m());
    }
}

class A {
    public int m() {
        int i;
        boolean b;
        double x;
        int y;
        double z;
        
        i = 0;
        b = true;
        x = 2.5;
        y = 42;
        z = 3.0;
        
        if (b) {
            System.out.println(x);
        } else {
            
        }

        if (y < i) {
            System.out.println(100);
        } else {
            System.out.println(x * 2.0);
            System.out.println(z);
        }
        return 99;
    }
}
