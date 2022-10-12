package com.learn.test;

import com.learn.dao.UserDao;
import com.learn.dao.impl.UserDaoImpl;
import com.learn.pojo.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    UserDao userDao = new UserDaoImpl();
    @Test
    void queryByUsername() {

        if(userDao.queryByUsername("admin") == null){
            System.out.println("用户名可用");
        }else {
            System.out.println("用户名已存在!");
        }
    }

    @Test
    void queryByUsernameAndPassword() {
        if(userDao.queryByUsernameAndPassword("admin","admin123456") == null){
            System.out.println("用户名或密码错误，登陆失败");
        }else {
            System.out.println("查询成功");
        }
    }

    @Test
    void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"wzg168","123456","wzg168@qq.com")));
    }
}