package cn.zc.stone.parser;

import cn.zc.stone.ast.ASTree;
import cn.zc.stone.lexer.CodeDialog;
import cn.zc.stone.lexer.Lexer;
import cn.zc.stone.lexer.ParseException;
import cn.zc.stone.lexer.Token;

/**
 * Created by zero on 2016/10/27.
 */
public class ParserRunner {
    public static void main(String[] args) throws ParseException {
        Lexer l=new Lexer(new CodeDialog());
        BasicParser bp=new BasicParser();
        while (l.peek(0)!= Token.EOF){
            ASTree ast=bp.parse(l);
            System.out.println("=> "+ast.toString());
        }
    }
}
