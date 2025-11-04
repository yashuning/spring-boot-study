package com.nys.study.spring.springbootstudy.wheel.classloader;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 演示类加载器的父子关系
 * @date 2023/12/5 22:25
 */
public class T004_ParentAndChild {

    public static void main(String[] args) {
        // AppClassLoader
        System.out.println(T004_ParentAndChild.class.getClassLoader());
        // null -- AppClassLoader 加载器的类加载器 -- Bootstrap 类加载器，也就是AppClassLoader是由 Bootstrap 类加载器加载的
        System.out.println(T004_ParentAndChild.class.getClassLoader().getClass().getClassLoader());
        // AppClassLoader加载器的父加载器 --> PlatformClassLoader
        // 注意：jdk9 之后用来代替 ExtClassLoader 加载器
        System.out.println(T004_ParentAndChild.class.getClassLoader().getParent());
        // PlatformClassLoader 加载器的父加载器 --> null(Bootstrap 类加载器)
        System.out.println(T004_ParentAndChild.class.getClassLoader().getParent().getParent());
        //System.out.println(T004_ParentAndChild.class.getClassLoader().getParent().getParent().getParent());

    }

}
