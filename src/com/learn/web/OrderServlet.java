package com.learn.web;

import com.learn.pojo.Cart;
import com.learn.pojo.User;
import com.learn.service.OrderService;
import com.learn.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet{

    private OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先获取Cart购物车对象
        Cart cart =(Cart)req.getSession().getAttribute("cart");
        //获取UserId
       User loginUser = (User)req.getSession().getAttribute("user");
       if(loginUser == null){
           req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
           return;
       }
       Integer userId = loginUser.getId();
        //调用OrderService.createOrder(Cart,userId)生成订单
        String orderId = orderService.createOrder(cart, userId);
            //需要给每一个Servlet中每一个方法调用Service.xxx()都需要加上try-catch

        //req.setAttribute("orderId",orderId);
        //请求转发到/pages/cart/checkout.jsp
    //    req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);
        req.getSession().setAttribute("orderId",orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
