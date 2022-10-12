package com.learn.web;

import com.learn.pojo.Book;
import com.learn.pojo.Page;
import com.learn.service.BookService;
import com.learn.service.impl.BookServiceImpl;
import com.learn.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求的参数 pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2、调用bookServuce.page(pageNo,pageSize):page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3、保存page对象到request域中
        req.setAttribute("page",page);
        //4、请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }


    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
         pageNo+=1;
        //获取请求参数封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //调用bookService.addBook保存图书
        bookService.addBook(book);
        //跳到图书列表页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);

    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        bookService.deleteBookById(id);
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

     Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
     bookService.updateBook(book);
     resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo=" +req.getParameter("pageNo"));
    }
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.queryBooks();
        req.setAttribute("books",books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求的图书编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
         //查询图书
        Book book = bookService.queryBookById(id);
        //保存到request域中
        req.setAttribute("book",book);
        //请求转发
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);

    }
}
