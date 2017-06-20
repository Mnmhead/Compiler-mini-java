package AST;
import AST.Visitor.Visitor;
import java.util.*;
import Semantics.*;

public class MethodDecl extends ASTNode {
    public Type t;
    public Identifier i;
    public FormalList fl;
    public VarDeclList vl;
    public StatementList sl;
    public Exp e;
    public MethodSymbolTable mst;
    
    public MethodDecl(Type at, Identifier ai, FormalList afl, VarDeclList avl, 
                      StatementList asl, Exp ae, int ln) {
        super(ln);
        t=at; i=ai; fl=afl; vl=avl; sl=asl; e=ae; mst = null;
    }

    /*
     * Sets the MethodSymbolTable for this MethodDecl node.
     */
    public void setMethodSymbolTable( MethodSymbolTable mst ) {
       this.mst = mst;
    }  

    /*
     * Function to build a MiniJava type from this method decl.
     * 
     * @returns the MiniJava Method type that represents this method decl.
     */
    public MJMethod getMiniJavaType() {
        MJType return_type = t.getMiniJavaType();
   
        List<MJType> args = new ArrayList<MJType>();
        // Add the argument types to list in order
        for( int i = 0; i < fl.size(); i++ ) {
            Formal arg = fl.get( i );
            MJType arg_type = arg.t.getMiniJavaType(); 
            args.add( arg_type );
        } 
      
        MJMethod methodType = new MJMethod( return_type, args ); 
        return methodType;
    }

    /*  
     * This function determines if this MethodDecl is attempting to override some
     * other MethodDecl in an earlier scope.
     *
     * @returns the type of the method this MethodDecl is attempting to override,
     *          returns null if this method is not attempting an override.
     */
    public MJMethod isOverriding() {
       if (mst == null) {
           System.out.println("method " + i.s + " mst is null");   
       }
       ClassSymbolTable currentScope = mst.getParentTable();

       GlobalSymbolTable gst = GlobalSymbolTable.getInstance();
       ClassTree ct = gst.classTree;
       ClassSymbolTable nextScope = ct.getParentClassST( currentScope.type );
       if( nextScope == null ) {
          // method did not exist in any parent scope
          return null;
       }
       
       // Now call getMethodType and get the method type (if it exists) of 
       // a super class's version of this method.
       String method_name = i.s; 
       return nextScope.getMethodType( method_name );
    }   
 
    public void accept(Visitor v) {
        v.visit(this);
    }
}
