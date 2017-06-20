class Main {
    public static void main(String[] args) {
        System.out.println(99);
    }
}

class A {
    public int f() {
        int i;
        {
            i = 5;
            i = 2;
            if (false)
                i = 7;
            else
                i = 9;
        }
        return i;
    }
}
