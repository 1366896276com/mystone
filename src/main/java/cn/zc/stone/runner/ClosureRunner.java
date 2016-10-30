package cn.zc.stone.runner;

import cn.zc.stone.evaluator.ClosureEvaluator;
import javassist.gluonj.util.Loader;

/**
 * Created by zero on 2016/10/29.
 */
public class ClosureRunner {
    public static void main(String[] args) throws Throwable {
        Loader.run(ClosureInterpreter.class,args,ClosureEvaluator.class);
    }
}
