package cn.zc.stone.runner;

import cn.zc.stone.env.NestedEnv;
import cn.zc.stone.lexer.ParseException;
import cn.zc.stone.parser.ClosureParser;

/**
 * Created by zero on 2016/10/29.
 */
public class ClosureInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new ClosureParser(),new NestedEnv());
    }
}
