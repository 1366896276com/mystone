package cn.zc.stone.evaluator;

import cn.zc.stone.ast.ASTree;
import cn.zc.stone.ast.ArrayLiteral;
import cn.zc.stone.ast.ArrayRef;
import cn.zc.stone.ast.PrimaryExpr;
import cn.zc.stone.env.Environment;
import cn.zc.stone.lexer.StoneException;
import cn.zc.stone.parser.ArrayParser;
import javassist.gluonj.Require;
import javassist.gluonj.Reviser;

import java.util.List;

/**
 * Created by zero on 2016/10/30.
 */
@Require({FuncEvaluator.class, ArrayParser.class})
@Reviser
public class ArrayEvaluator {
    @Reviser
    public static class ArrayListEx extends ArrayLiteral {
        public ArrayListEx(List<ASTree> list) {
            super(list);
        }
        public Object eval(Environment env){
            int s=numChildren();
            Object[]res=new Object[s];
            int i=0;
            for (ASTree t:this)
                res[i++]=((BasicEvaluator.ASTreeEx)t).eval(env);
            return res;
        }
    }
    @Reviser
    public static  class ArrayRefEx extends ArrayRef {
        public ArrayRefEx(List<ASTree> list) {
            super(list);
        }
        public Object eval(Environment env,Object value){
            if (value instanceof Object[]){
                Object index=((BasicEvaluator.ASTreeEx)index()).eval(env);
                if (index instanceof Integer)
                    return ((Object[])value)[(Integer)index];
            }
            throw new StoneException("bad array access",this);
        }
    }
    @Reviser
    public static class AssignEx extends BasicEvaluator.BinaryEx {
        public AssignEx(List<ASTree> list) {
            super(list);
        }

        @Override
        protected Object computeAssign(Environment env, Object rightval) {
            ASTree le=left();
            if(le instanceof PrimaryExpr){
                FuncEvaluator.PrimaryEx p=(FuncEvaluator.PrimaryEx)le;
                if(p.hasPostfix(0)&& p.postfix(0) instanceof ArrayRef){
                    Object a=((FuncEvaluator.PrimaryEx)le).evalSubExpr(env,1);
                    if (a instanceof  Object[]) {
                        ArrayRef aref = (ArrayRef) p.postfix(0);
                        Object index = ((BasicEvaluator.ASTreeEx) aref.index()).eval(env);
                        if (index instanceof Integer) {
                            ((Object[]) a)[(Integer)index]=rightval;
                            return rightval;
                        }
                    }
                    throw new StoneException("bad array access",this);
                }
            }
            return super.computeAssign(env, rightval);
        }
    }
}
