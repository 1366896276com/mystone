package cn.zc.stone.ast;

import java.util.Iterator;

/**
 * Created by zero on 2016/10/23.
 */
public abstract class ASTree implements Iterable<ASTree> {
    public abstract String location();
    public abstract ASTree child(int i);
    public abstract int numChildren();
    public abstract Iterator<ASTree> children();
    public Iterator<ASTree> iterable(){return children();}


}
