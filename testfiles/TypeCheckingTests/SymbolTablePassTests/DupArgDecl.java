class DupArgDecl {
   public static void main( String[] args ) {
      System.out.println( 100 );
   }
}

class B {
   int[] a;
}

class A {
   int a;

   public int uhhhh( int i, int i, int i, int i, int i, boolean j, int j,
                     int[] p, B p ) {
      return 0;
   }
}
