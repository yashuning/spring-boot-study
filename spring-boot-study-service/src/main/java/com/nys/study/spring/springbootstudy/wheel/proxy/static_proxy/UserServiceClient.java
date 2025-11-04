package com.nys.study.spring.springbootstudy.wheel.proxy.static_proxy;

import com.nys.study.spring.springbootstudy.wheel.proxy.UserService;
import com.nys.study.spring.springbootstudy.wheel.proxy.UserServiceImpl;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 静态代理
 * @date 2023/10/22 5:20 PM
 */
public class UserServiceClient {

    public static void main(String[] args) {
        UserService userServiceImpl = new UserServiceImpl();
        UserService proxy = new UserServiceProxy(userServiceImpl);

        proxy.select();
        proxy.update();
    }

}
