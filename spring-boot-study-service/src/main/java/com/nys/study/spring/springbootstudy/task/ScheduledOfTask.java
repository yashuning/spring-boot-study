package com.nys.study.spring.springbootstudy.task;

import com.nys.study.spring.springbootstudy.common.enums.ScheduledStatusEnum;
import com.nys.study.spring.springbootstudy.dao.component.SpringUtils;
import com.nys.study.spring.springbootstudy.dto.SpringScheduledCronDto;
import com.nys.study.spring.springbootstudy.service.api.impl.SpringScheduledCronServiceImpl;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 定时任务task
 * @date 2023/9/21 3:50 PM
 */
public interface ScheduledOfTask extends Runnable {

    /**
     * 所有定时任务类只需要实现这个接口并相应的在数据库插入一条记录，那么在微服务启动的时候，就会被自动注册到Spring的定时任务里
     */

    /**
     * 实现控制定时任务启用或禁用的功能
     */
    @Override
    default void run() {
        SpringScheduledCronServiceImpl impl = SpringUtils.getBean(SpringScheduledCronServiceImpl.class);
        SpringScheduledCronDto scheduledCron = impl.findByCronKey(this.getClass().getName());
        if (ScheduledStatusEnum.DISABLED.getCode().equals(scheduledCron.getStatus())) {
            // 任务是禁用状态
            return;
        }
        execute();
    }

    /**
     * 定时任务方法
     */
    void execute();
}
