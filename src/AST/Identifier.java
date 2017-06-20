package AST;
import AST.Visitor.Visitor;
import Semantics.MJType;

public class Identifier extends ASTNode {
  public String s;
  public MJType t;

  public Identifier(String as, int ln) { 
    super(ln);
    s=as;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public String toString(){
    return s;
  }
}
