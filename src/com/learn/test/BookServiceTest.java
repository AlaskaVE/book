package com.learn.test;

import com.learn.pojo.Book;
import com.learn.service.BookService;
import com.learn.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
private BookService bookService = new BookServiceImpl();
    @Test
    void addBook() {
        bookService.addBook(new Book(null,"天下","1125",new BigDecimal(10000),10000,0,null));
    }

    @Test
    void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    void updateBook() {
        bookService.updateBook(new Book(22,"社会","1125",new BigDecimal(9999),10,1111,null));
    }

    @Test
    void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(1,4));
    }

    @Test
    public void pageByPrice(){
        System.out.println(bookService.pageByPrice(1,4,10,50));
    }
}