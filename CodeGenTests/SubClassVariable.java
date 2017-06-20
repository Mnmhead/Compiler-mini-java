class Sub {
   public static void main(String[] args ) {
      
      System.out.println( new B().mainB() );
}
}


class A {

   int p;

   public boolean setP() {
      p = 900;
      System.out.println( p );
      return true;
   }
}

class B extends A {

   int p;

   public  int mainB() {
      boolean i;
      i = this.setP();
      return this.getP();
   }
   
   public int getP() {
      System.out.println( p );
      p = 420;
      return p;
   }
}
