package cn.zc.stone.runner;

import javassist.gluonj.util.Loader;

/**
 * Created by zero on 2016/10/30.
 */
public class NativeRunner {
    public static void main(String[]args) throws Throwable {
        Loader.run(NativeInterpreter.class,args,NativeEvaluator.class,ClosureEvaluator.class);
    }
}
