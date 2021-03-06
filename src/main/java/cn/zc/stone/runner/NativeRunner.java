package cn.zc.stone.runner;

import cn.zc.stone.evaluator.ClosureEvaluator;
import cn.zc.stone.evaluator.NativeEvaluator;
import javassist.gluonj.util.Loader;

/**
 * Created by zero on 2016/10/30.
 */
public class NativeRunner {
    public static void main(String[]args) throws Throwable {
        Loader.run(NativeInterpreter.class,args,NativeEvaluator.class,ClosureEvaluator.class);
    }
}
