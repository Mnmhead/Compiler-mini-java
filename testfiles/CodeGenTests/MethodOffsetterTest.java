class MethodOffsetterTest {
   public static void main(String[] args) {
      System.out.println( 100 );
   }
}

class Vehicle {
   int weight;

   public boolean isFast() {
      return false;
   }

   public Vehicle get() {
      return this;
   }

   public int[] getParts() {
      return new int[ 10000 ];
   }
}

class Car extends Vehicle {

   public boolean isFast() {
      return true;
   }

   public Car get() {
      return this;
   }
}

class Boat extends Vehicle {

   public boolean isFast() {
      return true;
   }

   public Boat get() {
      return this;
   }
}

class Plane extends Vehicle {

   int speed;
   
   public boolean isFast() {
      return true;
   }

   public boolean canFly() {
      return true;
   }

   public int altitude() {
      return 37000;
   }

   public boolean increaseSpeed( int x, int y ) {
      speed = speed + (x * x) + (y*y) + 100 - 990;
      return true;
   }
}
