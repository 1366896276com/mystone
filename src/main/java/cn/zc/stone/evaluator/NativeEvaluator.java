package cn.zc.stone.evaluator;

import cn.zc.stone.ast.ASTree;
import cn.zc.stone.env.Environment;
import cn.zc.stone.env.NativeFunction;
import cn.zc.stone.lexer.StoneException;
import javassist.gluonj.Require;
import javassist.gluonj.Reviser;


import java.util.List;

/**
 * Created by zero on 2016/10/30.
 */
@Require(FuncEvaluator.class)
@Reviser
public class NativeEvaluator  {
    @Reviser
    public static class NativeArg extends FuncEvaluator.ArgumentsEx {
        public NativeArg(List<ASTree> list) {
            super(list);
        }

        @Override
        public Object eval(Environment callerEnv, Object value) {
            if(!(value instanceof NativeFunction)){
                return super.eval(callerEnv,value);
            }
            NativeFunction function=(NativeFunction)value;
            int nparams=function.numOfParameters();
            if (size()!=nparams)
                throw new StoneException("bad number of arguments",this);
            Object[] args=new Object[nparams];
            int num=0;
            for (ASTree a:this){
                BasicEvaluator.ASTreeEx ae=(BasicEvaluator.ASTreeEx)a;
                args[num++]=ae.eval(callerEnv);
            }
            return function.invoke(args,this  );
        }
    }
}
