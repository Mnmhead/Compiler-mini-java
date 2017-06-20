class Main {
    public static void main(String[] args) {
        System.out.println(new A().m());
    }
}

class A {
    public int m() {
        int i;
        i = 1;
        System.out.println(i);
        i = i + 1;
        System.out.println(i);
        i = i + 1;
        return i;        
    }
}
