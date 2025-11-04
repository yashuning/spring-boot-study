package com.nys.study.spring.springbootstudy.main;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: TODO
 * @date 2024/1/2 20:34
 */
public class MainTest {


    public static void main(String[] args) {
//        try {
//            System.out.println("try code start");
//            // 开启异步线程执行逻辑
//            Thread asyncThread = new Thread(() -> {
//                // 异步执行的逻辑
//                try {
//                    Thread.sleep(5000); // 模拟异步操作
//                    System.out.println("Async operation completed");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//            asyncThread.start(); // 启动异步线程
//            System.out.println("try code asyncThread start");
//
//        } finally {
//            // finally 块中的代码
//            System.out.println("Finally block executed");
//        }
//
//        // 主方法结束
//        System.out.println("Main method finished");


        try {
            // 开启异步线程执行逻辑
            Thread asyncThread = new Thread(() -> {
                // 异步执行的逻辑
                try {
                    Thread.sleep(3000); // 模拟异步操作
                    System.out.println("Async operation completed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            asyncThread.start(); // 启动异步线程

            // 主线程继续执行其他操作
            System.out.println("Main thread continues");

        } finally {
            // finally 块中的代码会在异步方法执行完成后执行
            System.out.println("Finally block executed");
        }
    }
}
