package cn.zc.stone.env;

/**
 * Created by zero on 2016/10/29.
 */
public interface Environment {
    void put(String name,Object value);
    Object get(String name);
}
