// Gyorgy Wyatt Muntean, Justin Adsuara Copyright (c) 2017

import java.io.*;
// import CSE401.MiniJava; if we put things in packages, junit and ant
// shit themselves again.... :(

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class MiniJavaTest {

   @Test
   public void testScan() {
      final String TEST_FILES_DIR = "../../ScannerTests";    
      File test_dir = new File( TEST_FILES_DIR );
/* 
      int res = 0; 
      for( File f : test_dir.listFiles() ) {
         // res = MiniJava.scan( f.getName(), false ); 
      }*/
   }

   @Test
   public void testParseCommandLineArgs() {
      assertTrue( 1 == 1 );
   }

}
