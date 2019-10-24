package edu.nf.info.dao.factory;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author YXD
 * @date 2019/10/24
 */
public class DaoProxyInvocationHandler implements InvocationHandler {
    private Object daoProxy;
    private SqlSession sqlSession;
    public DaoProxyInvocationHandler(Object daoProxy,SqlSession sqlSession){
        this.daoProxy=daoProxy;
        this.sqlSession=sqlSession;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try{
            return method.invoke(daoProxy,args);
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            sqlSession.close();
        }
    }
}
