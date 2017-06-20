// Copyright (c) Gyorgy Wyatt Muntean, Justin Adsuara 2017

package CodeGen;

/**
 * This class writes to System.out. 
 *
 */
public class AssemblyWriter {

   public AssemblyWriter() {}

   public void genLabel( String l ) {
      System.out.println( l + ":" );
   }

   public void gen( String s ) {
      System.out.println( "   " + s );
   }

   public void comment( String c ) {
      System.out.println( "#" + c );
   }

   public void genLine( ) {
      System.out.println( );
   }
}
