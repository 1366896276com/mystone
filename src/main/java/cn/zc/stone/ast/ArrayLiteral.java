package cn.zc.stone.ast;

import java.util.List;

/**
 * Created by zero on 2016/10/30.
 */
public class ArrayLiteral  extends  ASTList{
    public ArrayLiteral(List<ASTree> list) {
        super(list);
    }
    public  int size(){return numChildren();}
}
