package cn.zc.stone.env;

import cn.zc.stone.ast.BlockStmnt;
import cn.zc.stone.ast.ParameterList;

/**
 * Created by zero on 2016/10/29.
 */
public class Function {
    protected ParameterList parameters;
    protected BlockStmnt body;
    protected Environment env;

    public Function(ParameterList parameters, BlockStmnt body, Environment env) {
        this.parameters=parameters;
        this.body=body;
        this.env=env;
    }

    public ParameterList parameters(){return parameters;}
    public BlockStmnt body(){return body;}
    public Environment makeEnv(){return new NestedEnv(env);}

    @Override
    public String toString() {
        return "<fun:"+hashCode()+">";
    }
}