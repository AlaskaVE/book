package com.learn.test;

import com.learn.dao.BookDao;
import com.learn.dao.impl.BookDaoImpl;
import com.learn.pojo.Book;
import com.learn.pojo.Page;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoTest {
private BookDao bookDao = new BookDaoImpl();
    @Test
    void addBook() {
            bookDao.addBook(new Book(null,"国哥","191125",
                    new BigDecimal(9999),1100000,0,null));

    }

    @Test
    void deleteBookById() {
    }

    @Test
    void updateBook() {
        bookDao.updateBook(new Book(21,"大家","191125",new BigDecimal(9999),1100000,0,null));
    }

    @Test
    void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    void queryBooks() {
        for (Book b :bookDao.queryBooks()) {
            System.out.println(b);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,50));
    }

    @Test
    public void queryForPageItems() {
        for (Book book : bookDao.queryForPageItems(8, Page.PAGE_SIZE)) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageItemsByPrice() {
        for (Book book : bookDao.queryForPageItemsByPrice(0, Page.PAGE_SIZE,10,50)) {
            System.out.println(book);
        }
    }
}