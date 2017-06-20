class Main {
      public static void main(String[] args) {
          System.out.println(new A().m(1.0));
      }            
}

class A {
    public double m(double x) {
        return x * 2.0;
    }
}
