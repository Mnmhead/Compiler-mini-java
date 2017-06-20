class Main {
    public static void main(String[] args) {
        System.out.println(new A().m());
    }
}

class A {
    public int m() {

        int i;
        int j;

        i = 0;

        while (i < 10) {
            j = 0;
            while (j < i) {
                System.out.println(i * 10 + j);
                j = j + 1;
            }
            System.out.println(1000000);
            i = i + 1;
        }

        return 400200;
    }
    
}
