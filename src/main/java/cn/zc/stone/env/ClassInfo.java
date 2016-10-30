package cn.zc.stone.env;

import cn.zc.stone.ast.ClassBody;
import cn.zc.stone.ast.ClassStmnt;
import cn.zc.stone.lexer.StoneException;

/**
 * Created by zero on 2016/10/30.
 */
public class ClassInfo {
    protected ClassStmnt definition;
    protected Environment environment;
    protected ClassInfo superClass;
    public ClassInfo(ClassStmnt cs,Environment env){
        definition=cs;
        environment=env;
        Object obj=env.get(cs.surperClass());
        if(obj==null)
            superClass=null;
        else if (obj instanceof ClassInfo)
            superClass=(ClassInfo)obj;
        else
            throw new StoneException("unknown super class: "+cs.surperClass(),cs);
    }
    public String name(){return definition.name();}
    public ClassInfo superClass(){return superClass;}
    public ClassBody body(){return definition.body();}
    public Environment environment(){return environment;}

    @Override
    public String toString() {
        return "<class "+name()+">";
    }
}
