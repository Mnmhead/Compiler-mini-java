package AST;
import AST.Visitor.Visitor;

public class DoubleLiteral extends Exp {
  public double x;

  public DoubleLiteral(double ai, int ln) {
    super(ln);
    x=ai;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
