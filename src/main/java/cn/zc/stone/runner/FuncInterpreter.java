package cn.zc.stone.runner;

import cn.zc.stone.env.NestedEnv;
import cn.zc.stone.lexer.ParseException;
import cn.zc.stone.parser.FuncParser;

/**
 * Created by zero on 2016/10/29.
 */
public class FuncInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new FuncParser(),new NestedEnv());
    }
}
