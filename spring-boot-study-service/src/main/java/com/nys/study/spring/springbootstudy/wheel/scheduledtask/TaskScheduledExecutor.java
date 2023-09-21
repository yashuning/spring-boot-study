package com.nys.study.spring.springbootstudy.wheel.scheduledtask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: ScheduledExecutorService替代Timer，ScheduledExecutorService是从的java.util.concurrent里，做为并发工具类被引进的
 *      通过加入定时器元素，使executor也能融入定时任务。executor中，使用的线程池类为ScheduledExectorService，它可以执行定时任务和周期任务。
 * @date 2023/9/19 7:41 PM
 */
@Slf4j
@Service
public class TaskScheduledExecutor {

    /**
     * 介绍：ScheduledThreadPoolExecutor 本身就是一个线程池，支持任务并发执行。并且，其内部使用 DelayQueue 作为任务队列
     *
     * 相比于Timer，有以下好处：
     *      1、相比于Timer的单线程，它是通过线程池的方式来执行任务的
     *      2、多线程并行处理定时任务时，Timer运行多个TimeTask时，只要其中之一没有捕获抛出的异常，其它任务便会自动终止运行，使用ScheduledExecutorService则没有这个问题。
     *      3、可以很灵活的去设定第一次执行任务delay时间
     *
     * 缺点：不论是使用 Timer 还是 ScheduledExecutorService 都无法使用 Cron 表达式指定任务执行的具体时间。
     */

    private static TimerTask task = new TimerTask() {
        @Override
        public void run() {
            log.info("task运行了！时间为：{}", new Date());
        }
    };

    /**
     * 利用executor框架使程序在一个指定的时间点启动
     */
    public static void executeAtFixTime() throws Exception {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);// 线程池大小
        executor.schedule(
                task, // 需要运行的任务本身
                1, // 需要延迟的时间
                TimeUnit.SECONDS); // 需要延迟的单位
        // Task只会执行一次就结束
        Thread.sleep(20000);
        executor.shutdown();
    }

    /**
     * 周期任务 固定速率 是以上一个任务开始的时间计时， Period时间过去后，检测上一个任务是否执行完毕
     * 如果上一个任务执行完毕，则当前任务立即执行，如果上一个任务没有执行完毕，则需要等待上一个任务执行完毕后立即执行
     */
    public static void executeAtFixRate() throws Exception {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(task, 1, 3000, TimeUnit.MILLISECONDS);
        // 延迟1ms启动,每3s运行一次
        Thread.sleep(20000);
        executor.shutdownNow();
    }

    /**
     * 周期任务 固定延时 是以上一个任务结束时开始计时，period时间过去后，立即执行
     * 所以虽然设定是3s钟后执行一次，但实际上是4s执行一次，因为执行过程需要耗费1s
     */
    public static void executeAtFixDelay() throws Exception {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(task, 1, 3000, TimeUnit.MILLISECONDS);
        Thread.sleep(20000);
        executor.shutdown();
    }

}
