class Main {
    public static void main(String[] args) {
        System.out.println(99);
    }
}

class A {
    public int f() {
        int i;

        if (true)
            System.out.println(99);
        else
            i = 5;
        return i;
    }
}

