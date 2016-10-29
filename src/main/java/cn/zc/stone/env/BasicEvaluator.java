package cn.zc.stone.env;

import cn.zc.stone.ast.*;
import cn.zc.stone.lexer.StoneException;
import cn.zc.stone.lexer.Token;
import com.sun.org.apache.bcel.internal.generic.FADD;
import com.sun.org.apache.bcel.internal.generic.FALOAD;
import javassist.gluonj.Reviser;

import java.util.List;

/**
 * Created by zero on 2016/10/29.
 */
@Reviser
public class BasicEvaluator {
    public static final int TRUE = 1;
    public static final int FALSE = 0;

    @Reviser
    public static abstract class ASTreeEx extends ASTree {
        public abstract Object eval(Environment env);
    }

    @Reviser
    public static class ASTListEx extends ASTList {
        public ASTListEx(List<ASTree> list) {
            super(list);
        }

        public Object eval(Environment env) {
            throw new StoneException("cannot eval " + toString(), this);
        }
    }

    @Reviser
    public static class ASTLeafEx extends ASTLeaf {


        public ASTLeafEx(Token token) {
            super(token);
        }

        public Object eval(Environment env) {
            throw new StoneException("cannot eval " + toString(), this);
        }
    }

    @Reviser
    public static class NumberEx extends NumberLiteral {
        public NumberEx(Token token) {
            super(token);
        }

        public Object eval(Environment env) {
            return value();
        }
    }
    @Reviser
    public static class StringEx extends StringLiteral {
        public StringEx(Token token) {
            super(token);
        }

        public Object eval(Environment env) {
            return value();
        }
    }

    @Reviser
    public static class NameEx extends Name {

        public NameEx(Token token) {
            super(token);
        }

        public Object eval(Environment env) {
            Object value = env.get(name());
            if (value == null) {
                throw new StoneException("undifind name: " + name());
            } else {
                return value;
            }
        }
    }
    @Reviser
    public static class NegativeEx extends NegativeExpr {

        public NegativeEx(List<ASTree> list) {
            super(list);
        }

        public Object eval(Environment env) {
            Object v = ((ASTreeEx) operand()).eval(env);
            if (v instanceof Integer) {
                return new Integer(-((Integer) v).intValue());
            } else {
                throw new StoneException("bad type for -", this);
            }
        }
    }
    @Reviser
    public static class BinaryEx extends BinaryExpr {

        public BinaryEx(List<ASTree> list) {
            super(list);
        }

        public Object eval(Environment env) {
            String op = operator();
            if ("=".equals(op)) {
                Object right = ((ASTreeEx) right()).eval(env);
                return computeAssign(env, right);
            } else {
                Object left = ((ASTreeEx) right()).eval(env);
                Object right = ((ASTreeEx) left()).eval(env);
                return computeOp(left, op, right);
            }
        }

        protected Object computeOp(Object left, String op, Object right) {
            if (left instanceof Integer && right instanceof Integer) {
                return computeNumber((Integer) left, op, (Integer) right);
            } else if (op.equals("+")) {
                return String.valueOf(left) + String.valueOf(right);
            } else if (op.equals("==")) {
                if (left == null) {
                    return right == null ? TRUE : FALSE;
                } else {
                    return left.equals(right) ? TRUE : FALSE;
                }
            } else {
                throw new StoneException("bad type", this);
            }
        }

        protected Object computeNumber(Integer left, String op, Integer right) {
            int a = left.intValue();
            int b = right.intValue();
            if (op.equals("+"))
                return a + b;
            else if (op.equals("-"))
                return a - b;
            else if (op.equals("*"))
                return a * b;
            else if (op.equals("/"))
                return a / b;
            else if (op.equals("%"))
                return a % b;
            else if (op.equals("=="))
                return a == b ? TRUE : FALSE;
            else if (op.equals(">"))
                return a > b ? TRUE : FALSE;
            else if (op.equals("<"))
                return a < b ? TRUE : FALSE;
            else
                throw new StoneException("bad operator", this);
        }

        protected Object computeAssign(Environment env, Object rightval) {
            ASTree l = left();
            if (l instanceof Name) {
                env.put(((Name) l).name(), rightval);
                return rightval;
            } else {
                throw new StoneException("bad assingment ", this);
            }
        }


    }
    @Reviser
    public static class BlockEx extends BlockStmnt {
        public BlockEx(List<ASTree> list) {
            super(list);
        }

        public Object eval(Environment env) {
            Object result = 0;
            for (ASTree t : this) {
                if (!(t instanceof NullStmnt)) {
                    result = ((ASTreeEx) t).eval(env);
                }
            }
            return result;
        }
    }
    @Reviser
    public static class IfEx extends IfStmnt {
        public IfEx(List<ASTree> list) {
            super(list);
        }

        public Object eval(Environment env) {
            Object c = ((ASTreeEx) condition()).eval(env);
            if (c instanceof Integer && ((Integer) c).intValue() != FALSE) {
                return ((ASTreeEx) thenBlock()).eval(env);
            } else {
                ASTree b = elseBlock();
                if (b == null)
                    return 0;
                else
                    return ((ASTreeEx) b).eval(env);
            }
        }
    }
    @Reviser
    public static class WhileEx extends WhileStmnt {
        public WhileEx(List<ASTree> list) {
            super(list);
        }

        public Object eval(Environment env) {
            Object result = 0;
            for (; ; ) {
                Object c = ((ASTreeEx) condition()).eval(env);
                if (c instanceof Integer && ((Integer) c).intValue() == FALSE)
                    return result;
                else
                    result = ((ASTreeEx) body()).eval(env);
            }
        }
    }
}