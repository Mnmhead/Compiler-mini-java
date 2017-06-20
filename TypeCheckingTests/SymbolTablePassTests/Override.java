class KFOAIFPAFPENFPN {
   

   public 
static void main
(String[] arrrrrrrrggggg ) {
               System.out.println( 100 );
   }
}

class Dummy {
   int dummy;
}

class SmellyDummy extends Dummy {
   int smell;
}

class A {
   int i;

   public int incr() {
      i = i + 1;
      return i;
   }

   public int get() {
      return i;
   }

   public Dummy makeDummy() {
      return new Dummy();
   }

}


class B extends A {
   
   public boolean incr() {
      i = i + 2;
      return false;
   }

}

class C extends A {
   int c;

   public int get() {
      return c;
   }
}

class D extends A {
   int d;   
   
   // methods must have a return type that is a subtype of the
   // method they are overriding
   public SmellyDummy makeDummy() {
      return new SmellyDummy();
   }
}
