class Main {
    public static void main(String[] args) {
        {
            System.out.println(new A().m(1, 2, 3));
            System.out.println(new A().m(1, 2, 2));
            System.out.println(new A().m(3, 2, 3));
            System.out.println(new A().m(3, 2, 2));
        }
    }
}

class A {
    public int m(int x, int y, int z) {
        if (x < y) {
            if (y < z) {
                System.out.println(1);
            } else {
                System.out.println(2);
            }
        } else {
            if (y < z) {
                System.out.println(3);                
            } else {
                System.out.println(4);
            }
        }
        return 100;
    }
}

