package cn.zc.stone.lexer;

/**
 * Created by zero on 2016/10/23.
 */
public abstract class Token {
    public static final Token EOF=new Token(-1) {};
    public static final String EOL="\\n";
    private int lineNumber;

    public Token(int line) {
        lineNumber=line;
    }

    public int getLineNuber(){return lineNumber;}
    public boolean isIndetifier(){return false;}
    public boolean isNumber(){return false;}
    public boolean isString(){return false;}
    public int getNumber(){throw new StoneException("not number token");}
    public String getText(){return "";}
}
