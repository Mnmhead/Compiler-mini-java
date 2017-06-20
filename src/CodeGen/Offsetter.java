// Copyright (c) Gyorgy WYatt Muntean, Justin Adsuara 2017

package CodeGen;

import java.util.*;

/**
 * This class stores the memory offsets of object's fields.
 *
 */
public abstract class Offsetter {
   private Map<String,Map<String,Integer>> objects; 

   public abstract int getOffset( String className, String name );
}

