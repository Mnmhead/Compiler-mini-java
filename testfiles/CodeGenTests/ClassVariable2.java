class ClassVariable {
   public static void main( String[] args ) {
      {
      System.out.println( new Glob().getSize() ); 
      System.out.println( new Glob().doIt() );
      }
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

class Glob extends Goob {
   
   public int doIt() {
      int grub;
      size = 0;
      grub = 0;
      grub = grub + 69;
      size = (grub-grub-grub)*2;
      return size;
   }
}
