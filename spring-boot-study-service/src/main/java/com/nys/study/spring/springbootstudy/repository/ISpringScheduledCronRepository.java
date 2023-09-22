package com.nys.study.spring.springbootstudy.repository;

import com.nys.study.spring.springbootstudy.dto.SpringScheduledCronDto;

import java.util.List;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 定时任务
 * @date 2023/9/21 5:32 PM
 */
public interface ISpringScheduledCronRepository {

    List<SpringScheduledCronDto> listAll();

    SpringScheduledCronDto selectById(Long cronId);

    SpringScheduledCronDto selectByCronKey(String cronKey);
}
