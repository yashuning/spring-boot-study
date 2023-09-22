package com.nys.study.spring.springbootstudy.dao.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2023/09/21
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpringScheduledCronPO {
    /**
     * 字段: cron_id
     * 说明: 主键id
     */
    private Long cronId;

    /**
     * 字段: cron_key
     * 说明: 定时任务完整类名
     */
    private String cronKey;

    /**
     * 字段: cron_expression
     * 说明: cron表达式
     */
    private String cronExpression;

    /**
     * 字段: task_explain
     * 说明: 任务描述
     */
    private String taskExplain;

    /**
     * 字段: status
     * 说明: 状态,1:正常;2:停用
     */
    private Integer status;
}