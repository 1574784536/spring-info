package edu.nf.dao.test;

import edu.nf.info.dao.UserDao;
import edu.nf.info.entity.Users;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author YXD
 * @date 2019/10/22
 */
public class UserDaoTest {
    @Test
    public void testListUsers(){
        ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
        UserDao dao=context.getBean("UserDao",UserDao.class);
        List<Users> list=dao.listUsers();
        for (Users users : list) {
            System.out.println(users.getUserName());
        }
    }
}
