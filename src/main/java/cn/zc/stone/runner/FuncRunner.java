package cn.zc.stone.runner;

import cn.zc.stone.evaluator.FuncEvaluator;
import javassist.gluonj.util.Loader;

/**
 * Created by zero on 2016/10/29.
 */
public class FuncRunner {
    public static void main(String[] args) throws Throwable {
        Loader.run(FuncInterpreter.class,args,FuncEvaluator.class);
    }
}
