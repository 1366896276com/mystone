package cn.zc.stone.ast;

import cn.zc.stone.ast.ASTree;

import java.util.List;

/**
 * Created by zero on 2016/10/30.
 */
public class Dot extends PostFix {
    public Dot(List<ASTree> list) {
        super(list);
    }
    public String name(){return ((ASTLeaf)child(0)).token().getText();}

    @Override
    public String toString() {
        return "."+name();
    }
}
