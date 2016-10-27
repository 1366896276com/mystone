package cn.zc.stone.ast;

import cn.zc.stone.lexer.Token;


/**
 * Created by zero on 2016/10/25.
 */
public class StringLiteral extends ASTLeaf {
    public StringLiteral(Token token) {
        super(token);
    }
    public String value(){return token().getText();}
}
