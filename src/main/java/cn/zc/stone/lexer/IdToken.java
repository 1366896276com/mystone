package cn.zc.stone.lexer;

/**
 * Created by zero on 2016/10/23.
 */
public class IdToken extends Token {
    String text;

    public IdToken(int lineNo, String s) {
        super(lineNo);
        text=s;
    }

    @Override
    public boolean isIdentifier() {
        return true;
    }

    @Override
    public String getText() {
        return text;
    }
}
