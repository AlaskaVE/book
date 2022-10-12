package com.learn.test;

import com.learn.pojo.User;
import com.learn.service.UserService;
import com.learn.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserService userService = new UserServiceImpl();
    @Test
    void regisUser() {
        userService.regisUser(new User(null,"bbj168","666666","bbj168@qq.com"));
        userService.regisUser(new User(null,"abc168","666666","abc168@qq.com"));
    }

    @Test
    void login() {
        System.out.println(userService.login(new User(null,"wzg168","123456",null)));
    }

    @Test
    void existsUsername() {
        if(userService.existsUsername("wzg16888")){
            System.out.println("用户名已存在！");
        }else {
            System.out.println("用户名可用！");
        }
    }
}