class ClassVariable {
   public static void main( String[] args ) {
      System.out.println( new Goob().getSize() ); 
   }
}

class Goob {
   boolean can;
   int size;
   boolean should;

   public int getSize() {
      size = 69;
      return size;
   }
}
