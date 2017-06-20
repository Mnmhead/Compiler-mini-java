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

class BiPlane extends Plane {
   public int bangBang( int y, int[] pewPew, boolean x, Plane yyy ) {
      return y * pewPew[0]; // + speed;
   }
}

class TriPlane extends Plane {

}

class HUUUUUGEBIPLANE extends BiPlane {

}

class LikeAReallyBig extends HUUUUUGEBIPLANE {
   public BiPlane shitOutBiPlane(int speedy) {
      return new BiPlane();
   }
}

class A {

}

class B {

}

class F extends B {
   public int nooo() {
      return 7;
   }
}

class DICKLED {
   public int a() {
      return 0;
   }
 public int aqqq() {
      return 0;
   }

 public int qwa() {
      return 0;
   }
 public int eqa() {
      return 0;
   }
 public int adsf() {
      return 0;
   }
 public int g() {
      return 0;
   }
 public int f() {
      return 0;
   }

}  

class U extends DICKLED {

}

class Booo extends U {

}

class IOU extends Booo {

}

