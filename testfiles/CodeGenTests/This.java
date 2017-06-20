class Thiz {
   public static void main(String[] arp ) {
      System.out.println( new A().mainA() );
   }
}

class A {
   int a;

   public int mainA() {
      int joo;
      joo = this.getA();
      return joo;
   }
   
   public boolean setA( int l ) {
      a = l;
      return true;
   }

   public int getA() {
      boolean jaa;
      jaa = this.setA( 420 );
      return a;
   }
}
