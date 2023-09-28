package com.nys.study.spring.springbootstudy.service.api;

import com.nys.study.spring.springbootstudy.dto.SpringScheduledCronDto;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 定时任务service
 * @date 2023/9/21 7:50 PM
 */
public interface ISpringScheduledCronService {

    void initScheduledTask();

    Map<Long, ScheduledFuture<?>> initScheduledTaskAll();

    void addTask(SpringScheduledCronDto scheduledCron);

    void cancelTask(Long cronId);

    List<SpringScheduledCronDto> findAllTask();

    /**
     * 获取所有定时任务配置，通过缓存HashMap获取
     * @return
     */
    List<SpringScheduledCronDto> findAllTaskCache();

    SpringScheduledCronDto findByCronId(Long cronId);

    SpringScheduledCronDto findByCronKey(String cronKey);
}
