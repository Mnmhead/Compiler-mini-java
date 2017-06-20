class This2 {
   public static void main(String[] toot ) {
      System.out.println( new Maine().mainE() );
   }
}


class Maine {
   public int mainE() {
      A a;
      a = new C();
      return a.eat();
   }
}

class A {
   public int fart() {
      return 100;
   }

   public int eat() {
      return 22;
   }
}

class B extends A {

}

class C extends  B{
   public int eat() {
      return this.fart();
   }
}
