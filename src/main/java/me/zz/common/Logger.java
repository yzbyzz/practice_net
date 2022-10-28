package me.zz.common;

/**
 * @author qingzhi
 * @date 2022/10/28 16:39
 */
public class Logger {

    private Class clz;
    private String clzName;

    public Logger(Class clz) {
        this.clz = clz;
        clzName = "[" + clz.getName() + "]";
    }

    public void debug(String msg, Object... params) {
        output("[debug]" + margeParam(msg, params));
    }

    public void info(String msg, Object... params) {
        output("[info]" + margeParam(msg, params));
    }

    public void warn(String msg, Object... params) {
        output("[warn]" + margeParam(msg, params));
    }

    public void error(String msg, Object... params) {
        output("[error]" + margeParam(msg, params));
    }

    private void output(String msg) {
        System.out.println(msg);
    }

    private String margeParam(String msg, Object... params) {
        return clzName + String.format(msg, params);
    }
}
