package com.nys.study.spring.springbootstudy.wheel.proxy.dynamic_proxy_jdk;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 日志处理
 * @date 2023/10/31 11:10 AM
 */
@Slf4j
public class LogHandler implements InvocationHandler {

    private Object target;   // 被代理的对象，实际的方法执行者

    public LogHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);  // 调用 target 的 method 方法
        after();
        return result;
    }

    // 调用invoke方法之前执行
    private void before() {
        log.info(String.format("log start time [%s] ", new Date()));
    }
    // 调用invoke方法之后执行
    private void after() {
        log.info(String.format("log end time [%s] ", new Date()));
    }

}
