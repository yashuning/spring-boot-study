package com.nys.study.spring.springbootstudy.main.wheel.scheduledtask;

import com.nys.study.spring.springbootstudy.main.BaseTest;
import com.nys.study.spring.springbootstudy.wheel.scheduledtask.TaskScheduledExecutor;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: 测试
 * @date 2023/9/19 7:45 PM
 */
public class TaskScheduledExecutorTest extends BaseTest {

    @Test
    public void testTaskScheduledExecutor() throws Exception {
        TaskScheduledExecutor.executeAtFixTime(); // 利用executor框架使程序在一个指定的时间点启动
        TaskScheduledExecutor.executeAtFixRate(); // 固定速率执行，每3s执行一次
        TaskScheduledExecutor.executeAtFixDelay(); //固定延时执行，设定为延时3s执行一次，实际为4s执行一次
    }
}
