package com.liu.study.tiny.spi.user;

import com.liu.study.tiny.common.spi.IUserService;
import com.liu.study.tiny.common.spi.SuperClass;
import com.liu.study.tiny.common.spi.UserModel;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @desc
 * @author Liuweian
 * @version 1.0.0
 * @createTime 2020/5/29 13:24
 */
public class UserSpiTest {

    /**
     * 【注意】：
     *      java SPI也可以针对类，针对类的重写。
     */
    public static void main(String[] args) {
        // interfaceSpiTest();
        classSpiTest();
    }

    public static void interfaceSpiTest() {
        /**
         * 这里神奇的地方在于：这个工程里只引用了java-spi-provide工程，但是并没有在哪里new UserServiceImpl()
         * 实例对象，没有使用JVM就不会吧对应的类加载到JVM中。理论上这里我是获取不到UserServiceImpl实例对象的。
         * 但是这里却获取到了UserServiceImpl的实例对象。
         *
         * 这里就使用java的SPI技术，在META-INF/services下创建响应的文件{文件名称为接口的全限定名}，文件里面的内容
         * 为实现具体的实现类的全限定名。
         */
        ServiceLoader<IUserService> serviceLoader = ServiceLoader.load(IUserService.class);
        Iterator<IUserService> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            IUserService userService = iterator.next();
            System.out.println(userService.getClass());
            UserModel userModel = userService.getUser();
            System.out.println(userModel.toString());
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }

    public static void classSpiTest() {
        ServiceLoader<SuperClass> serviceLoader = ServiceLoader.load(SuperClass.class);
        Iterator<SuperClass> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            SuperClass superClass = iterator.next();
            System.out.println(superClass.getClass());
            superClass.classTypeTestMethod();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }

}
