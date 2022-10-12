package com.learn.service.impl;

import com.learn.dao.UserDao;
import com.learn.dao.impl.UserDaoImpl;
import com.learn.pojo.User;
import com.learn.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void regisUser(User user) {
      userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryByUsername(username) == null){
            //等于null，说明没查到，没查到表示可用
            return false;
        }
        return true;
    }
}
