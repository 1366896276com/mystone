package cn.zc.stone.ast;

import cn.zc.stone.ast.ASTree;

import java.util.Iterator;
import java.util.List;

/**
 * Created by zero on 2016/10/29.
 */
public class Arguments extends PostFix {

    public Arguments(List<ASTree> list) {
        super(list);
    }
    public int size(){return numChildren();}
}
