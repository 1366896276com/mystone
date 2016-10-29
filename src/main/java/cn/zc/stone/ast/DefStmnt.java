package cn.zc.stone.ast;

import cn.zc.stone.ast.ASTree;

import java.util.Iterator;
import java.util.List;

/**
 * Created by zero on 2016/10/29.
 */
public class DefStmnt extends ASTList {

    public DefStmnt(List<ASTree> list) {
        super(list);
    }
    
    public String name(){
        return ((ASTLeaf)child(0)).token().getText();
    }
    public ParameterList parameters(){
        return (ParameterList)child(1);
    }
    public BlockStmnt body(){
        return (BlockStmnt)child(2);
    }

    @Override
    public String toString() {
        return "(def "+name()+" "+parameters()+" "+body()+")";
    }
}
