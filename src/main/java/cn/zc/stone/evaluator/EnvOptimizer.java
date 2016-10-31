package cn.zc.stone.evaluator;

import static javassist.gluonj.GluonJ.revise;
import cn.zc.stone.ast.*;
import cn.zc.stone.env.Environment;
import cn.zc.stone.env.Symbols;
import cn.zc.stone.lexer.StoneException;
import cn.zc.stone.lexer.Token;
import javassist.gluonj.Require;
import javassist.gluonj.Reviser;
import org.omg.PortableInterceptor.LOCATION_FORWARD;

import java.util.List;

/**
 * Created by zero on 2016/10/31.
 */
@Require(ClosureEvaluator.class)
@Reviser
public class EnvOptimizer {
    @Reviser
    public static interface EnvEx2 extends Environment{
        Symbols symbols();
        void put(int nest,int index,Object value);
        Object get(int nest,int index);
        void putNew(String name,Object value);
        Environment where(String name);
    }
    @Reviser
    public static abstract class ASTreeOptEx extends ASTree{
        public void lookup(Symbols syms){}
    }
    @Reviser
    public static class ASTListEx extends ASTList {
        public ASTListEx(List<ASTree> list) {
            super(list);
        }

        public void lookup(Symbols syms){
            for(ASTree t:this){
                ((ASTreeOptEx)t).lookup(syms);
            }
        }
    }

    @Reviser
    public static class DefStmntEx extends DefStmnt {
        protected int index,size;
        public DefStmntEx(List<ASTree> list) {
            super(list);
        }

        public void lookup(Symbols syms){
           index=syms.putNew(name());
            size= FunEx.lookup(syms,parameters(),body());
        }
        public Object eval(Environment env){
            ((EnvEx2)env).put(0,index,new OptFunction(parameters(),body(),env,size)  );
            return  name();
        }
    }

    @Reviser
    public static class FunEx extends Fun {
        protected int size=-1;
        public FunEx(List<ASTree> list) {
            super(list);
        }

        public void lookup(Symbols syms){
            size=lookup(syms,parameters(),body());
        }

        public static int lookup(Symbols syms, ParameterList parameters, BlockStmnt body) {
            Symbols newSyms=new Symbols(syms);
            ((ParamsEx)parameters).lookup(newSyms);
            ((ASTreeOptEx)revise(body)).lookup(newSyms);
            return newSyms.size();
        }

        public Object eval(Environment env){
            return new OptFunction(parameters(),body(),env,size);
        }
    }

    @Reviser
    public static class ParamsEx extends ParameterList {
        protected int[] offsets=null;

        public ParamsEx(List<ASTree> list) {
            super(list);
        }

        public void lookup(Symbols syms){
            int s=size();
            offsets=new int[s];
            for (int i=0;i<s;i++)
                offsets[i]=syms.putNew(name(i));
        }

        public void eval(Environment env,int index,Object value){
            ((EnvEx2)env).put(0,offsets[index],value);
        }
    }

    @Reviser
    public static class NameEx extends Name{
        protected static final int UNKNOWN=-1;
        protected int nest,index;
        public NameEx(Token token) {
            super(token);
            index=UNKNOWN;
        }
        public void lookup(Symbols syms){
            Symbols.Location loc=syms.get(name());
            if (loc==null)
                throw new StoneException("undefined name: "+name(),this);
            else{
                nest=loc.nest;
                index=loc.index;
            }
        }
        public void lookupForAssign(Symbols syms){
            Symbols.Location loc=syms.put(name());
            nest=loc.nest;
            index=loc.index;
        }
        public Object eval(Environment env){
            if(index==UNKNOWN)
                return env.get(name());
            else
                return ((EnvEx2)env).get(nest,index);
        }

        public void evalForAssing(Environment env,Object value){
            if (index==UNKNOWN)
                env.put(name(),value);
            else
                ((EnvEx2)env).put(nest,index,value);
        }
    }
    @Reviser
    public static class BinaryEx2 extends BasicEvaluator.BinaryEx{
        public BinaryEx2(List<ASTree> list) {
            super(list);
        }
        public void lookup(Symbols syms){
            ASTree left=left();
            if ("=".equals((operator()))){
                if (left instanceof Name){
                    ((NameEx)left).lookupForAssign(syms);
                    ((ASTreeOptEx)right()).lookup(syms);
                    return;
                }
            }
            ((ASTreeOptEx)left).lookup(syms);
            ((ASTreeOptEx)right()).lookup(syms);
        }

        @Override
        protected Object computeAssign(Environment env, Object rightval) {
            ASTree l=left();
            if (l instanceof Name){
                ((NameEx)l).evalForAssing(env,rightval);
                return rightval;
            }else
                return super.computeAssign(env,rightval);
        }
    }

}
