package com.learn.web;

import com.learn.pojo.User;
import com.learn.service.UserService;
import com.learn.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User loginUser = userService.login(new User(null, username, password, null));

        if(loginUser != null){
            //登陆成功
            //跳到登陆成功页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }else {
            //把错误信息，和回显的表单项信息，保存到request域中
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);
            //跳回登陆页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }
}
