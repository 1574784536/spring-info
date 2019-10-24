package edu.nf.service.test;

import edu.nf.info.entity.Users;
import edu.nf.info.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author YXD
 * @date 2019/10/22
 */
public class UserServiceTest {
    @Test
    public void testListUsers(){
        ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
        UserService service=context.getBean("UserService",UserService.class);
        List<Users> list= service.listUsers();
        for (Users users : list) {
            System.out.println(users.getAddress());
        }
    }
}
