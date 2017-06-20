class Main {
    public static void main(String[] args) {
        {
            System.out.println(new A().m(false, false, false));
            System.out.println(new A().m(false, false, true));
            System.out.println(new A().m(false, true, false));
            System.out.println(new A().m(false, true, true));
            System.out.println(new A().m(true, false, false));
            System.out.println(new A().m(true, false, true));
            System.out.println(new A().m(true, true, false));
            System.out.println(new A().m(true, true, true));
        }
    }
}

class A {
    public int m(boolean x, boolean y, boolean z) {
        int i;

        i = 0;
        if (x) {
            if (y) {
                if (z) {
                    i = 7;
                } else {
                    i = 6;
                }
            } else {
                if (z) {
                    i = 5;
                } else {
                    i = 4;
                }
            }             
        } else {
            if (y) {
                if (z) {
                    i = 3;
                } else {
                    i = 2;
                }
            } else {
                if (z) {
                    i = 1;
                } else {
                    i = 0;
                }
            }    
        }
        return i;
    }
}
