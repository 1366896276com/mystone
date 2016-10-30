package cn.zc.stone.runner;

import cn.zc.stone.env.Natives;
import cn.zc.stone.env.NestedEnv;
import cn.zc.stone.lexer.ParseException;
import cn.zc.stone.parser.ClassParser;
import cn.zc.stone.parser.ClosureParser;

/**
 * Created by zero on 2016/10/30.
 */
public class ClassInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new ClassParser(), new Natives().environment(new NestedEnv()));
    }
}
