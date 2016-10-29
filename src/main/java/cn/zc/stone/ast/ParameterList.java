package cn.zc.stone.ast;

import cn.zc.stone.ast.ASTree;

import java.util.Iterator;
import java.util.List;

/**
 * Created by zero on 2016/10/29.
 */
public class ParameterList extends ASTList {

    public ParameterList(List<ASTree> list) {
        super(list);
    }
    public String name(int i){
        return ((ASTLeaf)child(i)).token().getText();
    }
    public int size(){
        return numChildren();
    }
}
