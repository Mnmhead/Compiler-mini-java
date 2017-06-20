class Main {
    public static void main(String[] args) {
        System.out.println(99);
    }
}

class A {
    public int f() {
        int i;
        boolean b;
        int[] arr;
        A a;

        i = 5 + true;
        i = false + 9;
        i = false + false;
        
        i = 3 - this;
        i = this - 5;
        i = this - this;
        
        i = 3 * this;
        i = this * 5;
        i = this * this;
        
        b = i < arr;
        b = arr < i;
        b = arr < arr;

        b = i && false;
        b = true && i;
        b = i && i;

        b = !i;

        i = arr[true];
        i = b[5];
        i = b[true];

        i = i.length;

        arr = new int[false];

        return i;
    }


}

class B {
    int i;
}
