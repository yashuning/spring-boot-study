package com.nys.study.spring.springbootstudy.wheel.scheduledtask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: spring 提供定时任务注解
 * @date 2023/9/19 7:53 PM
 */
@Slf4j
@Service
public class TaskSpringScheduled {

    private int i;

    /**
     * 每隔15秒执行一次方法
     */
    @Scheduled(cron = "*/15 * * * * ?")
    public void execute() {
        log.info("thread id:{},FixedPrintTask execute times:{}", Thread.currentThread().getId(), ++i);
    }
}
