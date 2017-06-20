package AST;
import AST.Visitor.Visitor;
import Semantics.*;

public class IntegerType extends Type {
  public IntegerType(int ln) {
    super(ln);
  }
  public MJType getMiniJavaType() {
    return IntType.getInstance(); 
  }
  public void accept(Visitor v) {
    v.visit(this);
  }
}
