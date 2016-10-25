package cn.zc.stone.ast;

import cn.zc.stone.lexer.Token;

/**
 * Created by zero on 2016/10/25.
 */
public class Name extends ASTLeaf {
    public Name(Token token) {
        super(token);
    }
    public String name(){return token().getText();}
}
