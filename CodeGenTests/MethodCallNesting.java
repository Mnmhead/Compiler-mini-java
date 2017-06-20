class MCN {

   public static void main(String[] args ) {

      System.out.println( new Add().add5( 10, 20, 30, 40, 
                          new Add().add5( new Add().add5( 109, 458, 434, 2319, 43211 ), 7, 8, 9, 10 ) ) );
   }
}

class Add {
   public int add5( int a, int b, int c, int d, int e ) {
      return a + b + c + d + e;
   }
}
