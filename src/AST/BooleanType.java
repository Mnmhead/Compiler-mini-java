package AST;
import AST.Visitor.Visitor;
import Semantics.*;

public class BooleanType extends Type {
  public BooleanType(int ln) {
    super(ln);
  }
  public MJType getMiniJavaType() {
    return BoolType.getInstance();
  }
  public void accept(Visitor v) {
    v.visit(this);
  }
}
