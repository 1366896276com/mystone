package cn.zc.stone.runner;

import cn.zc.stone.evaluator.ArrayEvaluator;
import cn.zc.stone.evaluator.ClassEvaluator;
import cn.zc.stone.evaluator.ClosureEvaluator;
import cn.zc.stone.evaluator.NativeEvaluator;
import javassist.gluonj.util.Loader;

/**
 * Created by zero on 2016/10/30.
 */
public class ArrayRunner {
    public  static  void main(String [] args) throws Throwable {
        Loader.run(ClassInterpreter.class, args, ClassEvaluator.class,
                ArrayEvaluator.class, NativeEvaluator.class,
                ClosureEvaluator.class);
    }
}
