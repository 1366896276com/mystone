package cn.zc.stone.env;
;
import static cn.zc.stone.runner.FuncEvaluator.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zero on 2016/10/29.
 */
public class NestedEnv implements Environment {
    protected Map<String,Object> values;
    protected Environment outer;
    public NestedEnv(){
        this(null);
    }

    public NestedEnv(Environment env) {
        values=new HashMap<String, Object>();
        outer=env;
    }

    public void setOuter(Environment outer) {
        this.outer = outer;
    }

    public void put(String name, Object value) {
        Environment e=where(name);
        if(e==null)
            e=this;
        ((EnvEx)e).putNew(name,value);
    }

    public Environment where(String name) {
        if(values.get(name)!=null){
            return this;
        }else if (outer==null)
            return null;
        else{
            EnvEx x=(EnvEx)outer;
            return x.where(name);
        }
    }

    public Object get(String name) {
        Object v=values.get(name);
        if (v==null && outer!=null){
            return outer.get(name);
        }else {
            return v;
        }
    }
    public void putNew(String name,Object value){
        values.put(name,value);
    }
}
