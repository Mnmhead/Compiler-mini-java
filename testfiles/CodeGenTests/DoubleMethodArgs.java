class Main {
      public static void main(String[] args) {
          {
              System.out.println(new A().m(1.0, 2.0, 3.0));
              System.out.println(new A().mix(5.0, 6));
              System.out.println(new A().mix2(8, 9.0));
              System.out.println(new A().mix3(11, 12.0, 13, 14.0));
          }
      }            
}

class A {
    public double m(double x, double y, double z) {
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
        return 4.0;
    }

    public double mix(double x, int y) {
        System.out.println(x);
        System.out.println(y);
        return 7.0;
    }

    public double mix2(int x, double y) {
        System.out.println(x);
        System.out.println(y);
        return 10.0;
    }

    public double mix3(int x, double y, int z, double a) {
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
        System.out.println(a);
        return 15.0;
    }
}
