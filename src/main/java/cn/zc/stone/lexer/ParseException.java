package cn.zc.stone.lexer;

import java.io.IOException;

/**
 * Created by zero on 2016/10/23.
 */
public class ParseException extends Throwable {
    public ParseException(IOException e) {
        super(e);
    }

    public ParseException(String msg) {
        super(msg);
    }

    public ParseException(Token t) {
        this("",t);
    }

    public ParseException(String msg,Token t) {
        super("syta error around "+location(t) +". "+msg);
    }

    private static String location(Token t) {
        if(t==Token.EOF)
            return "the last line";
        else
            return "\"" +t.getText()+"\" at line " +t.getLineNuber();
    }
}
