class Main {
    public static void main(String[] args) {
        {
            System.out.println(new A().m(0));
            System.out.println(new A().n());
            System.out.println(new A().o());
        }
    }
}

class A {
    int i;

    public int m(int i) {
        return i;
    }

    public int n() {
        int i;
        i = 1;
        return i;
    }

    public int o() {
        i = 2;
        return i;
    }
        
}


