package AST;
import AST.Visitor.Visitor;
import Semantics.*;

public abstract class Exp extends ASTNode {
    public MJType t;
    public Exp(int ln) {
        super(ln);
    }
    public abstract void accept(Visitor v);
}
