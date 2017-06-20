class Override2 {

   public static void main(String[] p ) {
      System.out.println( 100 );
   }

}

class A {
   int a;
      
   public A getA() {
      return this;
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

   public A getA() {
      return this;
   }
}

   
