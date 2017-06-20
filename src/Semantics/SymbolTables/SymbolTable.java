// Copyright (c) Gyorgy Wyatt Muntean, Justin Adsuara 2017

package Semantics;

import java.util.*;

/**
 * This class represents a symbol table to be used in Semantic Checking 
 * for MiniJava.
 */
public abstract class SymbolTable { 
   public abstract boolean addVarDecl( String name, MJType type );
}
