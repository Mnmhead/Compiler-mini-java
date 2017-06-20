class Main {
    public static void main(String[] args) {
        System.out.println(99);
    }
}

class A {
    public int f() {
        int i;
        while (false) {
            i = 7;
            if (true)
                i = 1;
            else
                i = 0;            
        }
        while (true) { i = i; }

        return i;
    }
}
