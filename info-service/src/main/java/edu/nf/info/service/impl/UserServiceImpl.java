package edu.nf.info.service.impl;

import edu.nf.info.dao.UserDao;
import edu.nf.info.entity.Users;
import edu.nf.info.service.UserService;

import java.util.List;

/**
 * @author YXD
 * @date 2019/10/22
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<Users> listUsers() {
        return userDao.listUsers();
    }
}
