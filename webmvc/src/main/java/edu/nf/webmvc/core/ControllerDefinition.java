package edu.nf.webmvc.core;

import java.lang.reflect.Method;

/**
 * @author YXD
 * @date 2019/10/23
 */
public class ControllerDefinition {
    private String controllerName;
    private Class<?> controllerClass;
    private Method method;

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public void setControllerClass(Class<?> controllerClass) {
        this.controllerClass = controllerClass;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
