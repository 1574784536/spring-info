package edu.nf.webmvc.core;

/**
 * @author YXD
 * @date 2019/10/23
 */
public class BeanNameUtils {
    public static String getBeanName(String name){
        String firstChar = name.substring(0, 1).toLowerCase();
        return firstChar + name.substring(1);
    }
}
