class D {
   public static void main( String[] args ) {
      {
      System.out.println( new Child().inherited( 8.88 ) );
      System.out.println( new Child().overidden( 69.420 ) );
      // expected result is: 8.88
      //                     33.33333 
      }
   }
}

class Parent {
   double x;
   double y;
   double z;   

   public double inherited( double x ) {
      return x;
   }
  
   public boolean setX( double set ) {
      x = set;
      return false;
   } 

   public double overidden( double a ) {
      return a;
   }
}


class Child extends Parent {

   public double overidden( double o ) {
      boolean f;
      f = this.setX( 33.33333 );
      return x;
   }
}
