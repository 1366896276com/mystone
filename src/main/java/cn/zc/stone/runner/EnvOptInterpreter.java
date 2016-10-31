package cn.zc.stone.runner;

import cn.zc.stone.ast.ASTree;
import cn.zc.stone.ast.NullStmnt;
import cn.zc.stone.env.BasicEnv;
import cn.zc.stone.env.Environment;
import cn.zc.stone.env.Natives;
import cn.zc.stone.env.ResizableArrayEnv;
import cn.zc.stone.evaluator.BasicEvaluator;
import cn.zc.stone.evaluator.EnvOptimizer;
import cn.zc.stone.lexer.CodeDialog;
import cn.zc.stone.lexer.Lexer;
import cn.zc.stone.lexer.ParseException;
import cn.zc.stone.lexer.Token;
import cn.zc.stone.parser.BasicParser;
import cn.zc.stone.parser.ClassParser;
import cn.zc.stone.parser.ClosureParser;

/**
 * Created by zero on 2016/11/1.
 */
public class EnvOptInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new ClosureParser(),new Natives().environment(new ResizableArrayEnv()));
    }

    public static void run(BasicParser bp, Environment env) throws ParseException {
        Lexer lexer=new Lexer(new CodeDialog());
        while (lexer.peek(0)!= Token.EOF){
            ASTree t= bp.parse(lexer);
            if (!(t instanceof NullStmnt)){
                ((EnvOptimizer.ASTreeOptEx)t).lookup(
                        ((EnvOptimizer.EnvEx2)env).symbols()
                );
                Object r=((BasicEvaluator.ASTreeEx)t).eval(env);
                System.out.println("=> "+r);
            }
        }
    }
}
