package com.liu.study.tiny.spi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @desc Mysql基于SPI开发数据库连接。
 * @author Liuweian
 * @version 1.0.0
 * @createTime 2020/5/29 9:37
 */
public class MysqlConnectorSpi {

    private final String URL = "jdbc:mysql://47.98.130.59:3306/spring_test?serverTimezone=GMT%2B8";

    private final String USER = "root";

    private final String PASSWORD = "123456";

    public static void main(String[] args) {
        /**
         * 1、老的使用了Class.forName("com.mysql.cj.jdbc.Driver")加载数据库驱动，然后通过DriverManager获取Connection。
         * 2、直接通过DriverManger获取连接。
         *
         * 解析：
         *      JVM是按需加载class的，即使你的classpath下有对应的.class文件，如果你没有使用，JVM也不会去主动把.class加载内存中。
         *
         *      1)、DriverManager.getConnection(...)构建一个Connection是需要一个Driver实例的，在老的流程，我们首先是通过Class.forName()
         *      加载mysql的驱动，在Mysql中的Driver的静态构造函数，会调用DriverManager.registerDriver(..)把Mysql的Driver注入到DriverManager。
         *
         *      2)、从JDK1.6开始引入了SPI（ServiceLoader），DriverManager从JDK7开始使用SPI机制。在DriverManager中的静态代码块中，使用了
         *      ServiceLoader加载对应的Driver实现，在构建一个ServiceLoader实例时，使用的Thread.currentThread().getContextClassLoader()的
         *      类加载器.
         *
         */
        MysqlConnectorSpi mysqlConnectorSpi = new MysqlConnectorSpi();
        mysqlConnectorSpi.oldExecuteDataOperator();
        mysqlConnectorSpi.newExecuteDataOperator();
    }

    public void newExecuteDataOperator() {
        Connection connection = null;

        Statement statement = null;

        try {
            // 01-建立与数据库的连接
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // 02-创建statement【翻译：陈述，声明】对象。statement对象用于执行静态SQL语句并返回结果
            statement = connection.createStatement();

            // 03-执行sql语句
            boolean result = statement.execute("select * from shop_user where userSeq = 1");

            System.out.println("执行结果：" + result);
        }  catch (SQLException e) {
            System.out.println("与数据库建立连接异常>>>>>>");
            e.printStackTrace();
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("statement关闭异常>>>>>>");
                    e.printStackTrace();
                }
                System.out.println("statement关闭成功>>>>>>");
            }
        }
    }

    public void oldExecuteDataOperator() {
        Connection connection = null;

        Statement statement = null;

        try {
            // 01-创建数据库启动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 02-建立与数据库的连接
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // 03-创建statement【翻译：陈述，声明】对象。statement对象用于执行静态SQL语句并返回结果
            statement = connection.createStatement();

            // 04-执行sql语句
            boolean result = statement.execute("select * from user where userSeq = 1");

            System.out.println(result);
        } catch (ClassNotFoundException e) {
            System.out.println("加载数据库驱动异常>>>>>>");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("与数据库建立连接异常>>>>>>");
            e.printStackTrace();
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("statement关闭异常>>>>>>");
                    e.printStackTrace();
                }
                System.out.println("statement关闭成功>>>>>>");
            }
        }
    }

}
