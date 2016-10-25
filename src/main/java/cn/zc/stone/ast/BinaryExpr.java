package cn.zc.stone.ast;

import java.util.List;

/**
 * Created by zero on 2016/10/25.
 */
public class BinaryExpr extends ASTList {
    public BinaryExpr(List<ASTree> list) {
        super(list);
    }

    public ASTree left(){return  child(0);}

    public ASTree right(){return child(2);}

    public  String operator(){
        return ((ASTLeaf)child(1)).token().getText();
    }
}
