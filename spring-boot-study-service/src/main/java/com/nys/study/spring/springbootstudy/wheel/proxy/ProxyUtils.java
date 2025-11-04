package com.nys.study.spring.springbootstudy.wheel.proxy;

import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 代理工具
 * @date 2023/10/31 11:30 AM
 */
@Slf4j
public class ProxyUtils {
    /**
     * 将根据类信息动态生成的二进制字节码保存到硬盘中，默认的是clazz目录下
     * params: clazz 需要生成动态代理类的类
     * proxyName: 为动态生成的代理类的名称
     */
    public static void generateClassFile(Class clazz, String proxyName) {
        // 根据类信息和提供的代理类名称，生成字节码

        byte[] classFile = new byte[]{};
        try {
            /**
             * JDK11中ProxyGenerator是包内可见导致无法调用，
             * 这个类在java.lang.reflect包内，使用反射调用
             */
            Class proxyGeneratorClass = Class.forName("java.lang.reflect.ProxyGenerator");
            Method m =proxyGeneratorClass.getDeclaredMethod("generateProxyClass",String.class,Class[].class);
            m.setAccessible(true);
            classFile = (byte[])m.invoke(proxyName, (Object) clazz.getInterfaces());
        } catch (Exception e){
            log.error("Failed to generate class file: " + e.getMessage());
        }
        String paths = clazz.getResource(".").getPath();
        log.info(paths);
        FileOutputStream out = null;
        try {
            //保留到硬盘中
            out = new FileOutputStream(paths + proxyName + ".class");
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
