// Copyright (c) Gyorgy Wyatt Muntean, Justin Adsuara 2017

package errorHandle;

/**
 * This class handles Parser Errors for a MiniJava compiler.
 */
public class ParserErrorHandler {
   public static final int ERROR_LIMIT = 69;
   public static int PARSER_ERRORS = 0;

    /*
    * Helper function to be called by the compiler's parser. 
    * This function should increment an error count and then
    * verify that the error count has not gotten out of control.
    * If the error count has surpassed a hard-limit, we exit!
    */
   public static void handleParserError() {
      PARSER_ERRORS += 1;
      if( PARSER_ERRORS > ERROR_LIMIT ) {
         System.err.println( "Over " + ERROR_LIMIT + " syntax errors encountered.\n"
                             + "Aborting compilation." );
         System.exit( 1 );
      }
   }
}
