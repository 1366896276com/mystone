package cn.zc.stone.ast;

import java.util.List;

/**
 * Created by zero on 2016/10/30.
 */
public class ClassStmnt extends ASTList {
    public ClassStmnt(List<ASTree> list) {
        super(list);
    }
    public String name(){return ((ASTLeaf)child(0)).token().getText();}
    public String surperClass(){
        if(numChildren()<3)
            return null;
        else
            return ((ASTLeaf)child(1)).token().getText();
    }
    public ClassBody body(){return (ClassBody)child(numChildren()-1);}
    public String toString(){
        String parent =surperClass();
        if(parent==null)
            parent="*";
        return  "(class "+name()+" "+parent+" "+body() +")";
    }
}
