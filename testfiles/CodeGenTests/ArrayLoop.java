class Main {
    public static void main(String[] args) {
        System.out.println(new A().m());
    }
}

class A {
    int[] x;
    public int m() {
        int i;
        
        x = new int[5];
        i = 0;
        while (i < x.length) {
            x[i] = i * 2;
            i = i + 1;
        }

        i = 0;
        while (i < x.length) {
            System.out.println(x[i]);
            i = i + 1;
        }
        return 1000;
    }
}
