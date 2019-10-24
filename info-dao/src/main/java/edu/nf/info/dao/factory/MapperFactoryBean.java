package edu.nf.info.dao.factory;

import edu.nf.info.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author YXD
 * @date 2019/10/24
 */
public class MapperFactoryBean<T> implements FactoryBean<T> {
    private Class<T> daoInterface;

    public void setDaoInterface(Class<T> daoInterface) {
        this.daoInterface = daoInterface;
    }

    @Override
    public T getObject() throws Exception {
        SqlSession session= MybatisUtils.getSqlSession(true);
        T daoProxy=session.getMapper(daoInterface);
        InvocationHandler handler=new DaoProxyInvocationHandler(daoProxy,session);
        return (T) Proxy.newProxyInstance(MapperFactoryBean.class.getClassLoader(),
                new Class[]{daoInterface},handler);
    }

    @Override
    public Class<?> getObjectType() {
        return daoInterface;
    }
}
