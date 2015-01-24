package org.articlezero.commandhelper;

import java.lang.reflect.Method;

/**
 * @author ArticleZero
 * @since 1/23/15
 */
public class CmdData {

    private Command cmd;
    private Class<?> clazz;
    private Method method;


    public CmdData(Command cmd, Class<?> clazz, Method method){
        this.cmd = cmd;
        this.clazz = clazz;
        this.method = method;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public Command getCmd() {
        return cmd;
    }

    public Method getMethod() {
        return method;
    }
}
