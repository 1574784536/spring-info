package edu.nf.web.test;

import edu.nf.info.web.UserController;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author YXD
 * @date 2019/10/22
 */
public class UserTest {
    @Test
    public void testListUsers(){
        ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
        UserController controller= context.getBean("userController",UserController.class);
    }
}
