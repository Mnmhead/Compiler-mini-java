class Main {
    public static void main(String[] args) {
        {
            System.out.println((new B()).m());
            System.out.println((new B()).n());
            System.out.println((new B()).o());
        }
    }
}

class A {
    public int m() {
        System.out.println(3);
        return 5;
    }

    public int n() {
        return 6;
    }

    public int o() {
        return 7;
    }
}


class B extends A {

}
