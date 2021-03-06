package AST;
import AST.Visitor.Visitor;

abstract public class ASTNode {
  // Instance variables
  
  // The line number where the node is in the source file, for use
  // in printing error messages about this AST node
  public int line_number;
  
  
  // Constructor
  public ASTNode(int ln) {
    line_number = ln;
  }

  public void printLineNumber() {
    System.out.print( " (line " + line_number + ") " );
  }
}
