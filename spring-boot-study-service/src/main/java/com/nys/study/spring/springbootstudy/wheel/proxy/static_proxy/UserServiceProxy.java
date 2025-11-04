package com.nys.study.spring.springbootstudy.wheel.proxy.static_proxy;

import com.nys.study.spring.springbootstudy.wheel.proxy.UserService;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: TODO
 * @date 2023/10/22 5:17 PM
 */
@Slf4j
public class UserServiceProxy implements UserService {

    /*
        静态代理
        优点：可以达到功能增强的目的，而且没有侵入原代码
        缺点
        1、 当需要代理多个类的时候，由于代理对象要实现与目标对象一致的接口，有两种方式：

            只维护一个代理类，由这个代理类实现多个接口，但是这样就导致代理类过于庞大
            新建多个代理类，每个目标对象对应一个代理类，但是这样会产生过多的代理类

        2、 当接口需要增加、删除、修改方法的时候，目标对象与代理类都要同时修改，不易维护。
     */

    private UserService target; // 被代理的对象

    public UserServiceProxy(UserService target) {
        this.target = target;
    }

    @Override
    public void select() {
        before();
        target.select();    // 这里才实际调用真实主题角色的方法
        after();
    }

    @Override
    public void update() {
        before();
        target.update();    // 这里才实际调用真实主题角色的方法
        after();
    }

    private void before() {     // 在执行方法之前执行
        log.info(String.format("log start time [%s] ", new Date()));
    }
    private void after() {      // 在执行方法之后执行
        log.info(String.format("log end time [%s] ", new Date()));
    }
}
