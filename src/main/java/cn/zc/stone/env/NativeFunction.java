package cn.zc.stone.env;

import cn.zc.stone.ast.ASTree;
import cn.zc.stone.lexer.StoneException;

import java.lang.reflect.Method;

/**
 * Created by zero on 2016/10/30.
 */
public class NativeFunction {
    protected Method method;
    protected String name;
    protected int numParams;
    public NativeFunction(String n,Method m){
        name=n;
        method=m;
        numParams=m.getParameterTypes().length;
    }

    @Override
    public String toString() {
        return "<native:"+hashCode()+">";
    }

    public int numOfParameters(){return numParams;}
    public Object invoke(Object[]args, ASTree tree){
        try {
            return method.invoke(null,args);
        }catch (Exception e){
            throw new StoneException("bad native function call:"+name,tree);
        }
    }
}
