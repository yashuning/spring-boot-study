package com.nys.study.spring.springbootstudy.config;

import com.nys.study.spring.springbootstudy.service.api.ISpringScheduledCronService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.annotation.Resource;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 定时任务配置
 * @date 2023/9/21 3:42 PM
 */
@Configuration
public class ScheduledConfig implements SchedulingConfigurer {

    /**
     * 默认 SchedulingConfigurer 使用的也是单线程的方式，如果需要配置多线程，则需要指定 PoolSize
     */

    @Resource
    private ISpringScheduledCronService springScheduledCronService;
    
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        // 初始化定时任务
        springScheduledCronService.initScheduledTask();
        // 其实这里也可以使用scheduledTaskRegistrar去添加任务的
        // 但是这样动态增加的时候,还是需要使用SpringScheduledCronRepository的add方法
        // 而且取消任务不方便 就放弃了在这里配置
        // 调用scheduledTaskRegistrar的方法就可以了 也很方便 看实际需求吧
    }
}
