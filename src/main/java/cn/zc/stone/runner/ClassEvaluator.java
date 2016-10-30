package cn.zc.stone.runner;

import cn.zc.stone.ast.*;
import cn.zc.stone.env.ClassInfo;
import cn.zc.stone.env.Environment;
import cn.zc.stone.env.NestedEnv;
import cn.zc.stone.env.StoneObject;
import cn.zc.stone.lexer.StoneException;
import javassist.gluonj.Require;
import javassist.gluonj.Reviser;
import static  cn.zc.stone.runner.FuncEvaluator.*;

import java.util.List;

/**
 * Created by zero on 2016/10/30.
 */
@Require(FuncEvaluator.class)
@Reviser
public class ClassEvaluator {
    @Reviser
    public static class ClassStmntEx extends ClassStmnt {
        public ClassStmntEx(List<ASTree> list) {
            super(list);
        }

        public Object eval(Environment env) {
            ClassInfo ci = new ClassInfo(this, env);
            ((EnvEx) env).put(name(), ci);
            return name();
        }
    }

    @Reviser
    public static class ClassBodyEx extends ClassBody {
        public ClassBodyEx(List<ASTree> list) {
            super(list);
        }

        public Object eval(Environment env) {
            for (ASTree t : this)
                ((BasicEvaluator.ASTreeEx) t).eval(env);
            return null;
        }
    }

    @Reviser
    public static class DotEx extends Dot {
        public DotEx(List<ASTree> list) {
            super(list);
        }

        public Object eval(Environment env, Object value) {
            String member = name();
            if (value instanceof ClassInfo) {
                if ("new".equals(member)) {
                    ClassInfo ci = (ClassInfo) value;
                    NestedEnv e = new NestedEnv(ci.environment());
                    StoneObject so = new StoneObject(e);
                    e.putNew("this", so);
                    initObject(ci, e);
                    return so;
                }
            } else if (value instanceof StoneObject) {
                try {
                    return ((StoneObject) value).read(member);
                } catch (StoneObject.AccessException e) {
                    e.printStackTrace();
                }
            }
            throw new StoneException("bad member access: " + member, this);
        }

        private void initObject(ClassInfo ci, NestedEnv env) {
            if(ci.superClass()!=null)
                initObject(ci.superClass(),env);
            ((ClassBodyEx)ci.body()).eval(env);
        }

    }
    @Reviser
    public  static class AssignEx extends BasicEvaluator.BinaryEx {
        public AssignEx(List<ASTree> list) {
            super(list);
        }

        @Override
        protected Object computeAssign(Environment env, Object rightval) {
            ASTree le=left();
            if(le instanceof PrimaryExpr){
                FuncEvaluator.PrimaryEx p=(FuncEvaluator.PrimaryEx) le;
                if (p.hasPostfix(0)&&p.postfix(0) instanceof  Dot){
                    Object t=((FuncEvaluator.PrimaryEx)le).evalSubExpr(env,1);
                    if (t instanceof StoneObject)
                        return setField((StoneObject)t,(Dot)p.postfix(0),rightval);
                }
            }
            return super.computeAssign(env,rightval);
        }

        private Object setField(StoneObject obj, Dot expr, Object rightval) {
            String name= expr.name();
            try {
                obj.write(name,rightval);
                return obj  ;
            } catch (StoneObject.AccessException e) {
                throw new StoneException("bad member access "+location()+": "+name);
            }
        }
    }
}
