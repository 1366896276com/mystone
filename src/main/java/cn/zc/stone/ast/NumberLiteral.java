package cn.zc.stone.ast;

import cn.zc.stone.lexer.Token;

/**
 * Created by zero on 2016/10/25.
 */
public class NumberLiteral extends ASTLeaf {
    public NumberLiteral(Token token) {
        super(token);
    }
    public  int value(){return token().getNumber();}
}
