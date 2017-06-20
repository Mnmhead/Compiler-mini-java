class DoubleFive {
   public static void main( String[] args ) {
      System.out.println( new AddDoubles().add( 3.14, 5.67, 10e4, 1.0, 50.0 ) );
   }
}

class AddDoubles {

   public double add( double a, double b, double c, double d, double e ) {
      return a + b + c + d + e;
   }

}
