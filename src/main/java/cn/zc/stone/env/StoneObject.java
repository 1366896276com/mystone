package cn.zc.stone.env;

import static cn.zc.stone.evaluator.FuncEvaluator.*;

/**
 * Created by zero on 2016/10/30.
 */
public class StoneObject {
    public  static class AccessException extends  Exception{}
    protected Environment env;
    public  StoneObject(Environment env){this.env=env;}

    @Override
    public String toString() {
        return "<object:"+ hashCode()+">";
    }
    public Object read(String member) throws AccessException {
        return getEnv(member).get(member);
    }
    public void write(String member,Object value) throws AccessException {
        ((EnvEx)getEnv(member)).putNew(member, value);
    }

    private Environment getEnv(String member) throws AccessException {
        Environment e=((EnvEx)env).where(member);
        if(e!=null&&e==env)
            return e;
        else
            throw new AccessException();
    }
}
