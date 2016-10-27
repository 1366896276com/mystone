package cn.zc.stone.ast;

import java.util.List;

/**
 * Created by zero on 2016/10/27.
 */
public class PrimaryExpr extends ASTList{
    public PrimaryExpr(List<ASTree> list) {
        super(list);
    }
    public  static ASTree create(List<ASTree>list){
        return list.size()==1?list.get(0):new PrimaryExpr(list );
    }
}
