package com.learn.test;

import com.learn.utils.JdbcUtils;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class JdbcUtilsTest {
    @Test
    public void testJdbcUtils(){
        Connection connection = JdbcUtils.getConnection();

        System.out.println(connection);

    }
}
