package cn.zc.stone.parser;


import cn.zc.stone.ast.*;
import cn.zc.stone.lexer.Lexer;
import cn.zc.stone.lexer.ParseException;
import cn.zc.stone.lexer.Token;
import cn.zc.stone.parser.Parser.Operators;

import java.util.HashSet;
import static cn.zc.stone.parser.Parser.rule;

/**
 * Created by zero on 2016/10/27.
 */
public class BasicParser {
    HashSet<String> reserved=new HashSet<String>();

    protected Parser.Operators operators=new Parser.Operators();

    protected Parser expr0=rule();

    protected Parser primary=rule(PrimaryExpr.class)
            .or(rule().sep("(").ast(expr0).sep(")"),
                    rule().number(NumberLiteral.class),
                    rule().identifier(Name.class,reserved),
                    rule().string(StringLiteral.class));

    protected Parser factor=rule().or(rule(NegativeExpr.class).sep("-").ast(primary),primary);

    protected Parser expr=expr0.expression(BinaryExpr.class,factor,operators);

    protected Parser statement0=rule();

    protected Parser block=rule(BlockStmnt.class)
            .sep("{").option(statement0)
            .repeat(rule().sep(";", Token.EOL).option(statement0))
            .sep("}");

    protected Parser simple=rule(PrimaryExpr.class).ast(expr);

    protected Parser statement=statement0.or(
            rule(IfStmnt.class).sep("if").ast(expr).ast(block).option(rule().sep("else").ast(block)),
            rule(WhileStmnt.class).sep("while").ast(expr).ast(block),
            simple
    );

    protected Parser program=rule().or(statement,rule(NullStmnt.class).sep(";",Token.EOL));


    public BasicParser(){
        reserved.add(";");
        reserved.add("}");
        reserved.add(Token.EOL);

        operators.add("=",1,Operators.RIGHT);
        operators.add("==",2,Operators.LEFT);
        operators.add(">",2,Operators.LEFT);
        operators.add("<",2,Operators.LEFT);
        operators.add("+",3,Operators.LEFT);
        operators.add("-",3,Operators.LEFT);
        operators.add("*",4,Operators.LEFT);
        operators.add("/",4,Operators.LEFT);
        operators.add("%",4,Operators.LEFT);

    }

    public ASTree parse(Lexer l) throws ParseException {
        return program.parse(l);
    }
}
