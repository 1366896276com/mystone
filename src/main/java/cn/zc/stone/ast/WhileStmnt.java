package cn.zc.stone.ast;

import java.util.List;

/**
 * Created by zero on 2016/10/27.
 */
public class WhileStmnt extends ASTList{
    public WhileStmnt(List<ASTree> list) {
        super(list);
    }

    public ASTree condition(){return child(0);}
    public ASTree body(){return child(0);}

    @Override
    public String toString() {
        return "(while "+condition()+" " + body()+")";
    }
}
