package com.nys.study.spring.springbootstudy.wheel.scheduledtask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: TimerTask
 * jdk自带的java.util.Timer类，这个类允许你调度一个java.util.TimerTask任务
 * @date 2023/9/18 7:25 PM
 */
@Slf4j
@Service
public class TaskTimer {

    /**
     * 介绍：
     *      Timer 内部使用一个叫做 TaskQueue 的类存放定时任务，它是一个基于最小堆实现的优先级队列。
     *      TaskQueue 会按照任务距离下一次执行时间的大小将任务排序，保证在堆顶的任务最先执行。这样在需要执行任务时，每次只需要取出堆顶的任务运行即可！
     * 缺陷：
     *      Timer 的任务的执行只能串行执行，一个任务执行时间过长的话会影响其他任务（性能非常差），再比如发生异常时任务直接停止（Timer 只捕获了 InterruptedException ）。
     */

    public void taskTimerRun() throws InterruptedException {

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                log.info("task1运行了！时间为：{}", new Date());
            }
        };

        log.info("当前时间：{}", new Date().toLocaleString());
        // 调度一个Task，当前时间1秒后，每两秒执行一次
        timer.schedule(task, 1000, 2000);

        Thread.sleep(10000);
        task.cancel(); // 取消当前的任务，这样开始每两秒运行一次的task就停止了

        log.info("=====================================");

        Calendar now = Calendar.getInstance();// 获取当前时间
        now.set(Calendar.SECOND, now.get(Calendar.SECOND) + 3);
        Date runDate = now.getTime();

        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                log.info("task2运行了！时间为：{}", new Date());
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        timer.scheduleAtFixedRate(task2, runDate, 3000);
        // 固定速率，这里是指从runDate（前面定义的当前时间后3秒钟）开始执行每3秒执行一次，实际运行间隔取线程进行时间和定义间隔间最长者
        Thread.sleep(20000);
        timer.cancel(); // 取消定时器
    }
}