package cn.zc.stone.runner;

import cn.zc.stone.ast.*;
import cn.zc.stone.env.Environment;
import cn.zc.stone.env.Function;
import cn.zc.stone.lexer.StoneException;
import javassist.gluonj.Require;
import javassist.gluonj.Reviser;

import java.util.List;

/**
 * Created by zero on 2016/10/29.
 */
@Require(BasicEvaluator.class)
@Reviser
public class FuncEvaluator {
    @Reviser
    public static interface EnvEx extends Environment {
        void putNew(String name, Object value);

        Environment where(String name);

        void setOuter(Environment e);
    }

    @Reviser
    public static class DefStmntEx extends DefStmnt {
        public DefStmntEx(List<ASTree> list) {
            super(list);
        }

        public Object eval(Environment env) {
            ((EnvEx) env).putNew(name(), new Function(parameters(), body(), env));
            return name();
        }
    }

    @Reviser
    public static class PrimaryEx extends PrimaryExpr {
        public PrimaryEx(List<ASTree> list) {
            super(list);
        }

        public ASTree operand() {
            return child(0);
        }

        public PostFix postfix(int nest) {
            return (PostFix) child(numChildren() - nest - 1);
        }

        public Object eval(Environment env) {
            return evalSubExpr(env, 0);
        }

        protected Object evalSubExpr(Environment env, int nest) {
            if (hasPostfix(nest)) {
                Object target = evalSubExpr(env, nest + 1);
                return ((PostFixEx) postfix(nest)).eval(env, target);
            } else
                return ((BasicEvaluator.ASTreeEx) operand()).eval(env);
        }

        protected boolean hasPostfix(int nest) {
            return numChildren() - nest > 1;
        }
    }

    @Reviser
    public static abstract class PostFixEx extends PostFix {
        public PostFixEx(List<ASTree> list) {
            super(list);
        }

        public abstract Object eval(Environment env, Object value);
    }

    @Reviser
    public static class ArgumentsEx extends Arguments {
        public ArgumentsEx(List<ASTree> list) {
            super(list);
        }

        public Object eval(Environment callerEnv, Object value) {
            if (!(value instanceof Function)) {
                throw new StoneException("bad function ", this);
            }
            Function func = (Function) value;
            ParameterList params = func.parameters();
            if (size() != params.size()) {
                throw new StoneException("bad number of arguments", this);
            }
            Environment newEnv = func.makeEnv();
            int num = 0;
            for (ASTree a : this)
                ((ParamsEx) params).eval(newEnv, num++, ((BasicEvaluator.ASTreeEx) a).eval(callerEnv));
            return ((BasicEvaluator.BlockEx) func.body()).eval(newEnv);
        }
    }

    @Reviser
    public static class ParamsEx extends ParameterList {
        public ParamsEx(List<ASTree> list) {
            super(list);
        }

        public void eval(Environment env, int index, Object value) {
            ((EnvEx) env).putNew(name(index), value);
        }

    }
}
