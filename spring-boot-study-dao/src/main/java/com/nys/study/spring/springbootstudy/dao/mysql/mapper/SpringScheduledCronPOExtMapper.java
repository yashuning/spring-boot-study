package com.nys.study.spring.springbootstudy.dao.mysql.mapper;

import com.nys.study.spring.springbootstudy.dao.mysql.entity.SpringScheduledCronPO;

import java.util.List;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 定时任务mapper
 * @date 2023/9/21 5:50 PM
 */
public interface SpringScheduledCronPOExtMapper extends SpringScheduledCronPOMapper {

    List<SpringScheduledCronPO> listAll();

    SpringScheduledCronPO selectById(Long cronId);

    SpringScheduledCronPO selectByCronKey(String cronKey);
}
