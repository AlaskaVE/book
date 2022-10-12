package com.learn.web;

import com.learn.pojo.Book;
import com.learn.pojo.Cart;
import com.learn.pojo.CartItem;
import com.learn.service.BookService;
import com.learn.service.impl.BookServiceImpl;
import com.learn.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数、商品编号、商品数量
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"),0);

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            cart.updateCount(id,count);
            //重定向回商品列表页面
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }
        /**
         * 清空购物车
         * @param req
         * @param resp
         * @throws ServletException
         * @throws IOException
         */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            //清空购物车
            cart.clear();
            //重定向回商品列表页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

        /**
         * 删除商品项
         * @param req
         * @param resp
         * @throws ServletException
         * @throws IOException
         */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号
     int id = WebUtils.parseInt(req.getParameter("id"),0);
     //获取购物车对象
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart != null){
            //删除了购物车商品项
            cart.deleteItem(id);
            //重定向回原来的购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

        /**
         * 加入购物车
         * @param req
         * @param resp
         * @throws ServletException
         * @throws IOException
         */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("加入购物车");
        System.out.println("商品编号：" +req.getParameter("id"));
        //获取请求参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //调用bookService.queryBookById(id):Book得到图书信息
        Book book = bookService.queryBookById(id);
        //把图书信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        //调用cart.addItem(cartItem);添加商品项
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);

        //最后一个添加的商品名称
        req.getSession().setAttribute("lastName",cartItem.getName());

        //重定向回商品列表页面
        resp.sendRedirect(req.getHeader("Referer"));

    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //调用bookService.queryBookById(id):Book得到图书信息
        Book book = bookService.queryBookById(id);
        //把图书信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        //调用cart.addItem(cartItem);添加商品项
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);

        //最后一个添加的商品名称
        req.getSession().setAttribute("lastName",cartItem.getName());
        //返回购物车总的商品数量和最后一个添加的商品名称
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);


    }

}
