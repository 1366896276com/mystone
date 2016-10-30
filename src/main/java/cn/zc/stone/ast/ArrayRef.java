package cn.zc.stone.ast;

import cn.zc.stone.ast.ASTree;

import java.util.List;

/**
 * Created by zero on 2016/10/30.
 */
public class ArrayRef extends PostFix {
    public ArrayRef(List<ASTree> list) {
        super(list);
    }
    public  ASTree index(){return child(0);}

    @Override
    public String toString() {
        return "["+index()+"]";
    }
}
