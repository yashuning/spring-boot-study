package com.nys.study.spring.springbootstudy.wheel.classloader;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 演示类加载器层级
 * @date 2023/12/5 22:03
 */
public class T002_ClassLoaderLevel {

    /*
        注意：父加载器不是“类加载器的加载器”，也不是“类加载器的父类加载器”
     */

    public static void main(String[] args) {
        // null -- bootstrap 类加载器
        System.out.println(String.class.getClassLoader());
        // AppClassLoader
        System.out.println(T002_ClassLoaderLevel.class.getClassLoader());
        // null AppClassLoader 加载器是由 bootstrap 类加载器加载的
        System.out.println(T002_ClassLoaderLevel.class.getClassLoader().getClass().getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
    }
}
