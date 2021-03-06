package cn.zc.stone.runner;

import cn.zc.stone.ast.ASTree;
import cn.zc.stone.ast.NullStmnt;
import cn.zc.stone.env.BasicEnv;
import cn.zc.stone.env.Environment;
import cn.zc.stone.evaluator.BasicEvaluator;
import cn.zc.stone.lexer.CodeDialog;
import cn.zc.stone.lexer.Lexer;
import cn.zc.stone.lexer.ParseException;
import cn.zc.stone.lexer.Token;
import cn.zc.stone.parser.BasicParser;

/**
 * Created by zero on 2016/10/29.
 */
public class BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new BasicParser(),new BasicEnv());
    }

    protected static void  run(BasicParser basicParser, Environment basicEnv) throws ParseException {
        Lexer lexer=new Lexer(new CodeDialog());
        while (lexer.peek(0)!= Token.EOF){
            ASTree t= basicParser.parse(lexer);
            if(!(t instanceof NullStmnt)){
                Object r=((BasicEvaluator.ASTreeEx)t).eval(basicEnv);
                System.out.println("=> "+r);
            }
        }
    }
}
