package AST;
import AST.Visitor.Visitor;
import Semantics.*;

public class IdentifierType extends Type {
  public String s;

  public IdentifierType(String as, int ln) {
    super(ln);
    s=as;
  }

  public MJType getMiniJavaType() {
    return new ClassType( s );
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
