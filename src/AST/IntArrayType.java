package AST;
import AST.Visitor.Visitor;
import Semantics.*;

public class IntArrayType extends Type {
  public IntArrayType(int ln) {
    super(ln);
  }
  public MJType getMiniJavaType() {
    return IntArray.getInstance();
  }
  public void accept(Visitor v) {
    v.visit(this);
  }
}
