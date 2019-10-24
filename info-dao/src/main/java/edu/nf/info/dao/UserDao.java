package edu.nf.info.dao;

import edu.nf.info.entity.Users;

import java.util.List;

/**
 * @author YXD
 * @date 2019/10/22
 */
public interface UserDao {
    List<Users> listUsers();
}
