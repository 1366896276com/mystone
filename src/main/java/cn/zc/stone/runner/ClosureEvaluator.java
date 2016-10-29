package cn.zc.stone.runner;

import cn.zc.stone.ast.*;
import cn.zc.stone.env.Environment;
import cn.zc.stone.env.Function;
import cn.zc.stone.lexer.StoneException;
import cn.zc.stone.lexer.Token;
import javassist.gluonj.Require;
import javassist.gluonj.Reviser;

import java.util.EventListener;
import java.util.List;

/**
 * Created by zero on 2016/10/29.
 */
@Require(FuncEvaluator.class)
@Reviser
public class ClosureEvaluator {
   @Reviser
    public static class FunEx extends Fun {
       public FunEx(List<ASTree> list) {
           super(list);
       }
       public Object eval(Environment env){
           return new Function(parameters(),body(),env);
       }
   }

}
