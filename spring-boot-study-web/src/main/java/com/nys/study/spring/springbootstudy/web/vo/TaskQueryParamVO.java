package com.nys.study.spring.springbootstudy.web.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 任务查询入参
 * @date 2023/9/22 4:11 PM
 */
@Data
public class TaskQueryParamVO implements Serializable {

    private Integer taskStatus;
}
