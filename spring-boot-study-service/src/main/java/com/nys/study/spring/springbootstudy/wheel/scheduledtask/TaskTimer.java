package com.nys.study.spring.springbootstudy.wheel.scheduledtask;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: TimerTask
 *      jdk自带的java.util.Timer类，这个类允许你调度一个java.util.TimerTask任务
 * @date 2023/9/18 7:25 PM
 */
@Slf4j
public class TaskTimer {

    public static void main(String[] args) throws InterruptedException {
        log.info("1111===");
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run() {
                log.info("task  run = {}", new Date());
            }
        };
        TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run() {
                log.info("task2  run = {}", new Date());
                //多线程并行处理定时任务时，Timer运行多个TimeTask时，只要其中之一没有捕获抛出的异常，其它任务便会自动终止运行，使用ScheduledExecutorService则没有这个问题。
                int i = 1/0;
            }
        };
        Timer timer = new Timer();
        log.info("begin: {}", new Date());
        //安排指定的任务在指定的时间开始进行重复的固定延迟执行。这里是延迟5秒开始执行，之后每3秒执行一次
        timer.schedule(timerTask, 5000, 3000);
//        Thread.sleep(6000);
//        timer.schedule(timerTask2, 5000, 3000);
    }
}
