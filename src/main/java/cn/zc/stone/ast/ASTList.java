package cn.zc.stone.ast;

import java.util.Iterator;
import java.util.List;

/**
 * Created by zero on 2016/10/25.
 */
public class ASTList extends ASTree {
    protected List<ASTree> children;

    public ASTList(List<ASTree> list) {
        children = list;
    }


    @Override
    public String location() {
        for (ASTree t : children) {
            String s = t.location();
            if (s != null)
                return s;
        }
        return null;
    }

    @Override
    public ASTree child(int i) {
        return children.get(i);
    }

    @Override
    public int numChildren() {
        return children.size();
    }

    @Override
    public Iterator<ASTree> children() {
        return children.iterator();
    }

    public Iterator<ASTree> iterator() {
        return children();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('(');
        String sp = "";
        for (ASTree t : children) {
            builder.append(sp);
            sp = " ";
            builder.append(t.toString());
        }
        return builder.append(')').toString();
    }
}
