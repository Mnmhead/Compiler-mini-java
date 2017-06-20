class Break {
   public static void main( String[] args ) {
      System.out.println( new Breaker().recurse( 20 ) );
   }

}

class Breaker {

   public int recurse( int x ) {
      //Breaker b;
      //b = new Breaker();
      return this.recurse( x - 1 );
   }

}
