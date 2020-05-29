package com.liu.study.tiny.spi.user.iml;

import com.liu.study.tiny.common.spi.IUserService;
import com.liu.study.tiny.common.spi.UserModel;

/**
 * @desc
 * @author Liuweian
 * @version 1.0.0
 * @createTime 2020/5/29 13:18
 */
public class UserServiceImpl implements IUserService {

    public UserModel getUser() {
        System.out.println("This is User Implement");
        UserModel user = new UserModel();
        user.setPassword("password");
        user.setUsername("张三");
        return user;
    }

}
