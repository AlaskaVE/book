package com.learn.dao;

import com.learn.pojo.User;

public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return 如果返回null说明没有这个用户
     */
    public User queryByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回null说明没有这个用户,或者用户名和密码有误
     */
    public User queryByUsernameAndPassword(String username,String password);

    /**
     * 保存用户信息
     * @param user
     * @return 返回-1表示操作失败，其他是sql语句影响的行数
     */
    public int saveUser(User user);



}
