package cn.zc.stone.lexer;

/**
 * Created by zero on 2016/10/23.
 */
public class LexerRunner {
    public static void main(String[] args) throws ParseException {
        Lexer l=new Lexer(new CodeDialog());

        for(Token t;(t=l.read())!=Token.EOF;){
            System.out.println("=> "+t.getText());
        }
    }
}
