package cn.zc.stone.evaluator;

import cn.zc.stone.ast.BlockStmnt;
import cn.zc.stone.ast.ParameterList;
import cn.zc.stone.env.ArrayEnv;
import cn.zc.stone.env.Environment;
import cn.zc.stone.env.Function;

/**
 * Created by zero on 2016/10/31.
 */
public class OptFunction extends Function {
   protected int size;


    public OptFunction(ParameterList parameters, BlockStmnt body, Environment env, int size) {
        super(parameters, body, env);
        this.size=size;
    }

    @Override
    public Environment makeEnv() {
        return new ArrayEnv(size,env);
    }
}
