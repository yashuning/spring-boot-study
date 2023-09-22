package com.nys.study.spring.springbootstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 定时任务信息
 * @date 2023/9/21 3:49 PM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpringScheduledCronDto {

    private Long cronId;

    /**
     * 定时任务完整类名
     */
    private String cronKey;

    /**
     * cron表达式
     */
    private String cronExpression;

    /**
     * 任务描述
     */
    private String taskExplain;

    /**
     * 状态,1:正常;2:停用
     */
    private Integer status;

}
