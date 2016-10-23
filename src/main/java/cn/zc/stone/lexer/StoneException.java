package cn.zc.stone.lexer;

/**
 * Created by zero on 2016/10/23.
 */
public class StoneException extends RuntimeException {
    public StoneException(String s) {
        super(s);
    }
    public StoneException(String m,ASTree t){
        super(m+" "+t.location());
    }
}
