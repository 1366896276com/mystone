package cn.zc.stone.parser;

import cn.zc.stone.ast.ClassBody;
import cn.zc.stone.ast.ClassStmnt;
import cn.zc.stone.ast.Dot;
import cn.zc.stone.lexer.Token;

import static cn.zc.stone.parser.Parser.rule;

/**
 * Created by zero on 2016/10/30.
 */
public class ClassParser extends  ClosureParser {
    Parser member=rule().or(def,simple);
    Parser class_body=rule(ClassBody.class).sep("{").option(member)
            .repeat(rule().sep(";", Token.EOL).option(member))
            .sep("}");
    Parser defclass=rule(ClassStmnt.class).sep("class").identifier(reserved)
            .option(rule().sep("extends").identifier(reserved))
            .ast(class_body);
    public ClassParser(){
        postfix.insertChoice(rule(Dot.class).sep(".").identifier(reserved));
        program.insertChoice(defclass);
    }
}
