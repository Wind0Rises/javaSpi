package com.liu.study.tiny.common.spi;

/**
 * @author Liuweian
 * @version 1.0.0
 * @desc
 * @createTime 2020/5/29 13:53
 */
public class UserModel {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
