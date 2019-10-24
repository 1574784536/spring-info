package edu.nf.webmvc.core;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YXD
 * @date 2019/10/23
 */
public class DispatcherServlet extends HttpServlet {
    /**
     * 初始化工作
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        //扫描
        List<String> classesName = scan();
        //解析控制器，找到相应注解的方法
        Map<String, ControllerDefinition> definitionMap = resolveController(classesName);
        //将map存入上下文作用域
        config.getServletContext().setAttribute("definitionMap", definitionMap);
        //创建spring容器
        String springConfig = config.getInitParameter("spring-config");
        ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
        //将ioc容器放入上下文作用域
        config.getServletContext().setAttribute("org.springframework.applicationContext", context);

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的url-pattern
        String requestURL = req.getServletPath();
        //匹配definitionMap中的key，找到相应的ControllerDefinition
        Map<String, ControllerDefinition> map = (Map<String, ControllerDefinition>)req.getServletContext().getAttribute("definitionMap");
        if(map.containsKey(requestURL)){
            ControllerDefinition definition = map.get(requestURL);
            //根据Definition信息调用控制器的请求处理方法
            try {
                invokeMethod(definition, req, resp);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }else{
            //不是业务请求，是其他如静态资源的访问url这些请求
            //就教会给容器的默认Servlet处理
            req.getServletContext().getNamedDispatcher("default").forward(req, resp);
        }
    }

    /**
     * 全局扫描
     * @return
     */
    private List<String> scan(){
        return ScanUtil.scanPackage();
    }

    /**
     * Map中的key保存请求的url，
     * ControllerDefinition保存了请求控制器的信息
     * @param classesName
     * @return
     */
    private Map<String, ControllerDefinition> resolveController(List<String> classesName) {
        Map<String, ControllerDefinition> definitionMap = new HashMap<>();
        for (String className : classesName) {
            try {
                Class<?> clazz = Class.forName(className);
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if(method.isAnnotationPresent(UrlPattern.class)){
                        String url = method.getAnnotation(UrlPattern.class).value();
                        ControllerDefinition definition = createDefinition(clazz, method);
                        definitionMap.put(url, definition);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return definitionMap;
    }

    /**
     * 创建控制器的描述定义
     * @param clazz
     * @param method
     * @return
     */
    private ControllerDefinition createDefinition(Class<?> clazz, Method method) {
        ControllerDefinition definition = new ControllerDefinition();
        definition.setControllerClass(clazz);
        definition.setControllerName(clazz.getSimpleName());
        definition.setMethod(method);
        return definition;
    }

    /**
     * 调用请求控制器的请求处理方法
     */
    private void invokeMethod(ControllerDefinition definition, HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Method requestMethod = definition.getMethod();
        //创建Controller实例
        //Object controllerInstance = definition.getControllerClass().newInstance();
        //从ioc容器中获取Bean对象（Controller）
        ApplicationContext context = (ApplicationContext) req.getServletContext().getAttribute("org.springframework.applicationContext");
        String beanName = BeanNameUtils.getBeanName(definition.getControllerName());
        Object controllerBean = context.getBean(beanName);
        requestMethod.invoke(controllerBean, req, resp);
    }
}
