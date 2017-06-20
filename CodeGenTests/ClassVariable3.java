class CV3 {
   public static void main(String[] args ) {
      System.out.println( new LizardBro().mainLizard( 77, 0 - 10 ) );
   }
}

class Animal {
   int size;
   boolean mammal;
   boolean reptile;
   boolean cute;
   int numOfLegs;

   public int getSize() {
      return size;
   }

   public int addLegs( int legs ) {
      numOfLegs = numOfLegs + legs;
      return numOfLegs;
   }

   public int type() {
      int type;
      if( mammal ) {
         type = 1;
      } else {
         type = 0 - 1;
      }
      if( reptile ) {
         type = 420;
      } else {
         type = 0 -1;
      }

      return type;
   }
}

class LizardBro extends Animal {
   int tailSize;

   public int mainLizard( int tail, int s ) {
      int temp;
      temp = this.setSize( tail, s );
      System.out.println( this.getTotalSize() );
      System.out.println( this.getSize() );
      System.out.println( this.getTailSize() );
      return 99999; 
   }

   public int getTotalSize() {
      return tailSize + size;
   }

   public int getSize() {
      return size;
   }   

   public int getTailSize() {
      return tailSize;
   }

   public int setSize( int tail, int s ) {
      size = s;
      tailSize = tail;
      return 0;
   }
}
