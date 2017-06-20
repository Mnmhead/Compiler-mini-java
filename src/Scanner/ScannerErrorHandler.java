// Copyright (c) Gyorgy Wyatt Muntean, Justin Adsuara 2017

package errorHandle;

/**
 * This class handles Scanner Errors for a MiniJava compiler. 
 */
public class ScannerErrorHandler {
   public static final int ERROR_LIMIT = 35;
   public static int SCANNER_ERRORS = 0; 

   /*
    * Helper function to be called by the compiler's scanner. 
    * This function should increment an error count and then
    * verify that the error count has not gotten out of control.
    * If the error count has surpassed a hard-limit, we exit!
    */
   public static void handleScannerError() {
      SCANNER_ERRORS += 1;
      if( SCANNER_ERRORS > ERROR_LIMIT ) {
         System.err.println( "Over " + ERROR_LIMIT + "unexpected symbols encountered.\n" 
                             + "Aborting compilation." );
         System.exit( 1 );
      }
   }
}
