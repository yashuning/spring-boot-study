package com.nys.study.spring.springbootstudy.wheel.proxy.dynamic_proxy_jdk;

import com.nys.study.spring.springbootstudy.wheel.proxy.ProxyUtils;
import com.nys.study.spring.springbootstudy.wheel.proxy.UserService;
import com.nys.study.spring.springbootstudy.wheel.proxy.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: TODO
 * @date 2023/10/31 11:14 AM
 */
public class LogHandlerClient {

    /**
     * 使用JDK动态代理的五大步骤:
     * 1.通过实现 InvocationHandler 接口来自定义自己的 InvocationHandler;
     * 2.通过 Proxy.getProxyClass 获得动态代理类
     * 3.通过反射机制获得代理类的构造方法，方法签名为 getConstructor(InvocationHandler.class)
     * 4.通过构造函数获得代理对象并将自定义的 InvocationHandler 实例对象传为参数传入
     * 5.通过代理对象调用目标方法
     */

    public static void main(String[] args) {
        // 设置变量可以保存动态代理类，默认名称以 $Proxy0 格式命名
        // System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 1. 创建被代理的对象，UserService接口的实现类
        UserService userService = new UserServiceImpl();
        // 2. 获取对应的 ClassLoader
        ClassLoader classLoader = userService.getClass().getClassLoader();
        // 3. 获取所有接口的Class，这里的UserServiceImpl只实现了一个接口UserService，
        Class[] interfaces = userService.getClass().getInterfaces();
        // 4. 创建一个将传给代理类的调用请求处理器，处理所有的代理对象上的方法调用
        InvocationHandler logHandler = new LogHandler(userService);
        /*
		   5.根据上面提供的信息，创建代理对象 在这个过程中，
               a.JDK会通过根据传入的参数信息动态地在内存中创建和.class 文件等同的字节码
               b.然后根据相应的字节码转换成对应的class，
               c.然后调用newInstance()创建代理实例
		 */
        UserService proxy = (UserService) Proxy.newProxyInstance(classLoader, interfaces, logHandler);
        // 调用代理的方法
        proxy.select();
        proxy.update();

        // 保存JDK动态代理生成的代理类，类名保存为 UserServiceProxy
        ProxyUtils.generateClassFile(userService.getClass(), "DynamicUserServiceProxy");
    }
}
