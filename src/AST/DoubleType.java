package AST;
import AST.Visitor.Visitor;
import Semantics.*;

public class DoubleType extends Type {
  public DoubleType(int ln) {
    super(ln);
  }
  public MJType getMiniJavaType() {
    return DoubleMJType.getInstance(); 
  }
  public void accept(Visitor v) {
    v.visit(this);
  }
}
