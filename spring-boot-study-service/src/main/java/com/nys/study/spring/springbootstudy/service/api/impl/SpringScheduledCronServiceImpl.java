package com.nys.study.spring.springbootstudy.service.api.impl;

import com.google.common.collect.Maps;
import com.nys.study.spring.springbootstudy.common.enums.ScheduledStatusEnum;
import com.nys.study.spring.springbootstudy.dto.SpringScheduledCronDto;
import com.nys.study.spring.springbootstudy.repository.impl.SpringScheduledCronRepository;
import com.nys.study.spring.springbootstudy.service.api.ISpringScheduledCronService;
import com.nys.study.spring.springbootstudy.task.ScheduledOfTask;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 定义了动态任务初始化, 增加, 取消, 查询方法
 * @date 2023/9/21 3:51 PM
 */
@Service
public class SpringScheduledCronServiceImpl implements ISpringScheduledCronService {

    @Autowired
    private ApplicationContext context;

    /**
     * 定时任务的线程池
     */
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private SpringScheduledCronRepository springScheduledCronRepository;

    /**
     * 存放所以定时任务实体类的Map
     */
    private static Map<Long, SpringScheduledCronDto> scheduledCronMap = Maps.newConcurrentMap();

    /**
     * 存放定时任务的Map key=cronId
     */
    private static Map<Long, ScheduledFuture<?>> scheduledCronFutureMap = Maps.newConcurrentMap();


    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        /*
            线程调度器设置只有一个线程容量，如果存在多个任务被触发时，会等第一个任务执行完毕才会执行下一个任务。所以这里还是需要自己去定义线程的大小
         */
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(3);                        // 线程池大小
        threadPoolTaskScheduler.setThreadNamePrefix("taskExecutor-");   // 线程名称
        threadPoolTaskScheduler.setAwaitTerminationSeconds(60);         // 等待时长
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);  // 调度器shutdown被调用时等待当前被调度的任务完成
        return threadPoolTaskScheduler;
    }


    /**
     * 初始化任务
     */
    @Override
    public void initScheduledTask() {
        // 查询数据库中所有的定时任务
        List<SpringScheduledCronDto> dtoList = springScheduledCronRepository.listAll();
        dtoList.forEach(this::addTask);
    }


    /**
     * 初始化任务
     */
    @Override
    public Map<Long, ScheduledFuture<?>> initScheduledTaskAll() {
        // 查询数据库中所有的定时任务
        List<SpringScheduledCronDto> dtoList = springScheduledCronRepository.listAll();
        dtoList.forEach(this::addTask);
        return scheduledCronFutureMap;
    }

    /**
     * 增加任务
     */
    @Override
    public void addTask(SpringScheduledCronDto scheduledCron) {
        // 1.先判定该定时任务的状态是否正常
        if (isTaskInactive(scheduledCron)) {
            return;
        }
        // 2.增加定时任务,map中是否存在了如果存在 移除取消
        if (scheduledCronMap.containsKey(scheduledCron.getCronId())) {
            cancelTask(scheduledCron.getCronId());
        }

        // 3.获取定时任务 使用 ApplicationContext去获取
        Object task = getTask(scheduledCron);

        Assert.isAssignable(ScheduledOfTask.class, task.getClass(), "定时任务类必须实现ScheduledOfTask接口");

        // 4.添加定时任务
        scheduleTask(scheduledCron, task);
    }

    /**
     * 取消定时任务
     */
    @Override
    public void cancelTask(Long cranId) {
        if (scheduledCronFutureMap.containsKey(cranId)) {
            ScheduledFuture<?> scheduledFuture = scheduledCronFutureMap.get(cranId);
            scheduledFuture.cancel(true);
            scheduledCronFutureMap.remove(cranId);
            scheduledCronMap.remove(cranId);
        }
    }

    @Override
    public List<SpringScheduledCronDto> findAllTask() {
        return springScheduledCronRepository.listAll();
    }

    /**
     * 查询现在所有的定时任务
     */
    @Override
    public List<SpringScheduledCronDto> findAllTaskCache() {
        return (List<SpringScheduledCronDto>) scheduledCronMap.values();
    }

    @Override
    public SpringScheduledCronDto findByCronId(Long cronId) {
        return springScheduledCronRepository.selectById(cronId);
    }

    @Override
    public SpringScheduledCronDto findByCronKey(String cronKey) {
        if (StringUtils.isBlank(cronKey)) {
            return null;
        }
        return springScheduledCronRepository.selectByCronKey(cronKey);
    }

    private boolean isTaskInactive(SpringScheduledCronDto scheduledCron) {
        return ScheduledStatusEnum.DISABLED.getCode().equals(scheduledCron.getStatus());
    }

    private Object getTask(SpringScheduledCronDto scheduledCron) {
        try {
            Class<?> clazz = Class.forName(scheduledCron.getCronKey());
            return context.getBean(clazz);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("spring_scheduled_cron表数据" + scheduledCron.getCronKey() + "有误", e);
        } catch (BeansException e) {
            throw new IllegalArgumentException(scheduledCron.getCronKey() + "未纳入到spring管理", e);
        }
    }

    private void scheduleTask(SpringScheduledCronDto scheduledCron, Object task) {
        // 可以通过改变数据库数据进而实现动态改变执行周期
        ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule((Runnable) task, triggerContext -> {
            // 通过当前ID查询到定时任务信息
            SpringScheduledCronDto scheduled = springScheduledCronRepository.selectById(scheduledCron.getCronId());
            // nextExecutionTime指向的是下一次执行的时间
            //这里每次配置就会查询数据库中的cron表达式 我们只需要更新数据库就可以了
            return new CronTrigger(scheduled.getCronExpression()).nextExecutionTime(triggerContext);
        });
        scheduledCronFutureMap.put(scheduledCron.getCronId(), schedule);
        scheduledCronMap.put(scheduledCron.getCronId(), scheduledCron);
    }

}
