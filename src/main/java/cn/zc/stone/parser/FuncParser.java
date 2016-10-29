package cn.zc.stone.parser;

import cn.zc.stone.ast.*;

import static cn.zc.stone.parser.Parser.rule;
/**
 * Created by zero on 2016/10/29.
 */
public class FuncParser extends BasicParser {
    Parser param=rule().identifier(reserved);
    Parser params=rule(ParameterList.class).ast(param)
            .repeat(rule().sep(",").ast(param));
    Parser paramList = rule().sep("(").maybe(params).sep(")");
    Parser args=rule(Arguments.class)
            .ast(expr)
            .repeat(rule().sep(",").ast(expr));
    Parser def=rule(DefStmnt.class)
            .sep("def")
            .identifier(reserved)
            .ast(paramList)
            .ast(block);

    Parser postfix=rule().sep("(").maybe(args).sep(")");

    public FuncParser(){
        reserved.add(")");
        primary.repeat(postfix);
        simple.option(args);
        program.insertChoice(def );
    }

}
