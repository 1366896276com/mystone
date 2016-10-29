package cn.zc.stone.parser;

import cn.zc.stone.ast.Fun;
import cn.zc.stone.parser.FuncParser;

/**
 * Created by zero on 2016/10/29.
 */
public class ClosureParser extends FuncParser{
    public ClosureParser(){
        primary.insertChoice(Parser.rule(Fun.class).sep("fun").ast(paramList).ast(block));
    }
}
