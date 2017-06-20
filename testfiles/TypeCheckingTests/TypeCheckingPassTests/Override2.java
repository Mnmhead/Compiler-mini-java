class Override2 {

   public static void main(String[] p ) {
      System.out.println( 100 );
   }

}

class A {
   int a;
      
   public int getA() {
      return a;
   }
}

class B extends A {
   int b;
}
class C extends B {
   int c;
}
class D extends C {
   int d;
}
class E extends D {
   int e;
}
class F extends E {
   int f;

   public int getA() {
      return f;
   }
}

   
