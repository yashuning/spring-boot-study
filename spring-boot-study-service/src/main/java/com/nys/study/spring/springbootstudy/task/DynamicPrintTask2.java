package com.nys.study.spring.springbootstudy.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 动态输出信息任务
 * @date 2023/9/21 7:31 PM
 */
@Component
@Slf4j
public class DynamicPrintTask2 implements ScheduledOfTask {

    private int i;

    @Override
    public void execute() {
        log.info("thread name:{} - id:{},DynamicPrintTask2 execute times:{}", Thread.currentThread().getName(), Thread.currentThread().getId(), ++i);
    }
}
