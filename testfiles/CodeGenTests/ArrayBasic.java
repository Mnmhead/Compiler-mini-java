class Main {
    public static void main(String[] args) {
        System.out.println(new A().m());
    }
}

class A {
    public int m() {
        int[] x;
        x = new int[5];
        System.out.println(x[3]);
        x[3] = 10;
        System.out.println(x[3]);
        x[3] = 5;
        System.out.println(x[3]);
        x[3] = 2;
        return x[3];
    }
    
}
