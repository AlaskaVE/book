package com.learn.web;

import com.learn.pojo.User;
import com.learn.service.UserService;
import com.learn.service.impl.UserServiceImpl;
import com.learn.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    protected void ajaxExistsusername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数username
        String username = req.getParameter("username");
        //调用userService.existsUsername();
        boolean existsUsername = userService.existsUsername(username);
        //把返回的结果封装成为map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
        /**
         * 注销
         * @param req
         * @param resp
         * @throws ServletException
         * @throws IOException
         */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、销毁session中用户登陆的信息（或者销毁session）
        req.getSession().invalidate();
        //2、重定向到首页或登陆页面
        resp.sendRedirect(req.getContextPath());
    }


        /**
         *处理登陆的功能
         * @param req
         * @param resp
         * @throws ServletException
         * @throws IOException
         */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User loginUser = userService.login(new User(null, username, password, null));

        if(loginUser != null){
            //登陆成功
            //保存用户登陆的信息到session域中
            req.getSession().setAttribute("user",loginUser);
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

    /**
     * 处理注册的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");


            User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());


        //2、检查验证码是否正确 先写死
        if(token != null && token.equalsIgnoreCase(code)){
            //正确，3、检查用户名是否可用
            if(userService.existsUsername(username)){
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("email",email);
                //不可用 跳回注册页面
                System.out.println("用户名【" +username+"】已存在！");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                //可用 调用service保存到数据库
                userService.regisUser(new User(null,username,password,email));
                //跳到注册成功页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else {
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            //不正确，跳回注册页面
            System.out.println("验证码错误[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

}
