class LocalVar {
   public static void main(String[] args ) {
      System.out.println( new A().locals() );
      // expected output is 0, 1, 175, -7
   }
}

class A {
   int i;

   public int locals() {
      int local;
      int banger;
      System.out.println( local );
      return local + new A().cool( local, new A().cool(1,1,100,25) , local, banger+7 );  
   }

   public int cool( int param, int two, int three, int four ) {
      System.out.println( two );
      return ((param + two) * three ) - four;
   }
}
