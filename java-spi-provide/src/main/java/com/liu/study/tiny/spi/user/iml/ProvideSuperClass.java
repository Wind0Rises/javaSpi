package com.liu.study.tiny.spi.user.iml;

import com.liu.study.tiny.common.spi.SuperClass;

/**
 * @desc  SPI   继承测试子类。
 * @author Liuweian
 * @version 1.0.0
 * @createTime 2020/5/29 14:12
 */
public class ProvideSuperClass extends SuperClass {

    @Override
    public void classTypeTestMethod() {
        System.out.println("This is Provide ^^^^^^^^^^ Super Type Test Method");
    }

}
