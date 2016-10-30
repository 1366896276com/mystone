package cn.zc.stone.parser;

import cn.zc.stone.ast.ArrayLiteral;
import cn.zc.stone.ast.ArrayRef;
import javassist.gluonj.Reviser;

import static cn.zc.stone.parser.Parser.rule;

/**
 * Created by zero on 2016/10/30.
 */
@Reviser
public class ArrayParser extends FuncParser {
    Parser elements=rule(ArrayLiteral.class)
            .ast(expr).repeat(rule().sep(",").ast(expr));
    public ArrayParser(){
        reserved.add("]");
        primary.insertChoice(rule().sep("[").maybe(elements).sep("]"));
        postfix.insertChoice(rule(ArrayRef.class).sep("[").ast(expr).sep("]"));

    }
}
