package cn.zc.stone.runner;

import javassist.gluonj.util.Loader;

/**
 * Created by zero on 2016/10/29.
 */
public class Ruuner {
    public static void main(String[] args) throws Throwable {
        Loader.run(BasicInterpreter.class,args,BasicEvaluator.class);
    }
}
