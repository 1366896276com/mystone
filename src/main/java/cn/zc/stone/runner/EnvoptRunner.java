package cn.zc.stone.runner;

import cn.zc.stone.evaluator.EnvOptimizer;
import cn.zc.stone.evaluator.NativeEvaluator;
import javassist.gluonj.util.Loader;

/**
 * Created by zero on 2016/11/1.
 */
public class EnvoptRunner {
    public static void main(String[] args) throws Throwable {
        Loader.run(EnvOptInterpreter.class,args, EnvOptimizer.class, NativeEvaluator.class);

    }
}
