class InvalidOverride {

   public static void main(String[] p ) {
      System.out.println( 0 );
   }
}

class A {
int a;

public int getA() {
      return a;
}
}


class B extends A {
   int b;
   boolean a;

   public int getA( boolean cool ) {
      if( cool ) {
         b =  1;  
      } else {
         b = 420;
      }

      return b;
   }
}
