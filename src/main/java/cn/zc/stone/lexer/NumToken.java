package cn.zc.stone.lexer;

/**
 * Created by zero on 2016/10/23.
 */
public class NumToken extends Token {
    private int value;
    public NumToken(int lineNo, int i) {
        super(lineNo);
        value=i;
    }

    @Override
    public boolean isNumber() {
        return true;
    }

    @Override
    public String getText() {
        return Integer.toString(value);
    }

    @Override
    public int getNumber() {
        return value;
    }
}
