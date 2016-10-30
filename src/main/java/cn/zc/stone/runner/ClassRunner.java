package cn.zc.stone.runner;

import javassist.gluonj.util.Loader;

/**
 * Created by zero on 2016/10/30.
 */
public class ClassRunner {
    public  static  void main(String[]args) throws Throwable {
        Loader.run(ClassInterpreter.class, args, ClassEvaluator.class,
                NativeEvaluator.class, ClosureEvaluator.class);
    }
}
