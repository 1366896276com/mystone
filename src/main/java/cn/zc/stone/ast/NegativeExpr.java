package cn.zc.stone.ast;

import java.util.List;

/**
 * Created by zero on 2016/10/27.
 */
public class NegativeExpr extends ASTList{
    public NegativeExpr(List<ASTree> list) {
        super(list);
    }

    public  ASTree operand(){return child(0);}
    public String toString(){
        return "-"+operand();
    }
}
