package com.nys.study.spring.springbootstudy.main.wheel.scheduledtask;

import com.nys.study.spring.springbootstudy.main.BaseTest;
import com.nys.study.spring.springbootstudy.wheel.scheduledtask.TaskTimer;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: task测试
 * @date 2023/9/19 5:14 PM
 */
public class TaskTimerTest extends BaseTest {

    @Resource
    private TaskTimer taskTimer;

    @Test
    public void testTaskTimerRun() throws InterruptedException {
        taskTimer.taskTimerRun();
    }

}