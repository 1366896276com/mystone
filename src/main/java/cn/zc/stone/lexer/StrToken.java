package cn.zc.stone.lexer;

/**
 * Created by zero on 2016/10/23.
 */
public class StrToken extends Token {
    String str;
    public StrToken(int lineNo, String s) {
        super(lineNo);
        str=s;
    }

    @Override
    public boolean isString() {
        return true;
    }

    @Override
    public String getText() {
        return str;
    }
}
