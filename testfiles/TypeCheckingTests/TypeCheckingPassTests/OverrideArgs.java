class OverrideArgs {

   public static void main(String[] p ) {
      System.out.println( 100 );
   }

}

class A {

}

class B {

}

class C {
    public int f(A a, B b, C c) {
        return 5;
    }
}

class D extends C {
    int z;
    
    public int f(A a, B b, C c) {
        return z;
    }
}
