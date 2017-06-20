package AST;
import AST.Visitor.Visitor;
import Semantics.ClassSymbolTable;

public abstract class ClassDecl extends ASTNode{
    public ClassSymbolTable cst;
    public Identifier i;
    
    public ClassDecl(int ln) {
        super(ln);
        cst = null;
    }

    public void setClassSymbolTable( ClassSymbolTable cst ) {
       this.cst = cst;
    }

    public abstract void accept(Visitor v);
}
