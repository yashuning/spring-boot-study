package com.nys.study.spring.springbootstudy.common.util;


import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Date;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: cron表达式工具
 * @date 2023/9/25 11:04 PM
 */
public class CronUtil {

    /**
     * 给定的Cron表达式的有效性
     *
     * @param cronExpression Cron表达式
     * @return
     */
    public static boolean isValidExpression(final String cronExpression) {
        return CronExpression.isValidExpression(cronExpression);
    }

    /**
     * 根据给定的Cron表达式返回下一个执行时间
     *
     * @param cronExpression
     * @return
     * @throws ParseException
     */
    public static Date getNextExecution(String cronExpression) throws ParseException {
        CronExpression cron = new CronExpression(cronExpression);
        return cron.getNextValidTimeAfter(new Date(System.currentTimeMillis()));
    }

}
