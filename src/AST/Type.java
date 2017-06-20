package AST;
import AST.Visitor.Visitor;
import Semantics.*;

public abstract class Type extends ASTNode {
    public Type(int ln) {
        super(ln);
    }

    /*
     * I sort of despise that we have to do this, but I don't
     * see another way. Unless we are ok with using a bunch of 
     * instanceof calls.
     */
    public abstract MJType getMiniJavaType();

    public abstract void accept(Visitor v);
}
