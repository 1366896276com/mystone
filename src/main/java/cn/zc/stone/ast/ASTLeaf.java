package cn.zc.stone.ast;

import cn.zc.stone.lexer.Token;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zero on 2016/10/25.
 */
public class ASTLeaf extends ASTree {
    private static List<ASTree> empty=new ArrayList<ASTree>();
    protected Token token;

    public ASTLeaf(Token token){this.token=token;}

    @Override
    public String location() {
        return "at line "+token.getLineNumber();
    }

    @Override
    public ASTree child(int i) {
       throw new IndexOutOfBoundsException();
    }

    @Override
    public int numChildren() {
        return 0;
    }

    @Override
    public Iterator<ASTree> children() {
        return empty.iterator();
    }

    public Iterator<ASTree> iterator() {
        return null;
    }

    @Override
    public String toString() {
        return token.getText();
    }

    public Token token(){
        return token;
    }
}
