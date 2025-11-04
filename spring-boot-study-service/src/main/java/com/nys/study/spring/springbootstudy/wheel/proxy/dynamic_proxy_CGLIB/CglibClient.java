package com.nys.study.spring.springbootstudy.wheel.proxy.dynamic_proxy_CGLIB;

import com.nys.study.spring.springbootstudy.wheel.proxy.UserServiceImpl;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: TODO
 * @date 2023/11/1 7:38 PM
 */
public class CglibClient {

    public static void main(String[] args) {
        LogInterceptor interceptor = new LogInterceptor();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);  // 设置超类，cglib是通过继承来实现的
        enhancer.setCallback(interceptor);

        UserServiceImpl userService = (UserServiceImpl) enhancer.create();   // 创建代理类
        userService.update();
        userService.select();
    }

}
