class Break {
   public static void main(String[] args ) {
      System.out.println( new Breaker().call() );
   }  
}  

class Breaker {
   public int call() {
      int a;
      int b;
      int c;
      a = this.call1();
      b = 420;
      return b;
   }

   public int call1() {
      int a;
      System.out.println( 69 );
      a = this.call2();
      return 9;
   }

   public int call2() {
      return 0;  
   }
}
