package com.nys.study.spring.springbootstudy.dao.mysql.mapper;

import com.nys.study.spring.springbootstudy.dao.mysql.entity.BasicUserInfoPO;

import java.util.List;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 用户信息Mapper
 * @date 2023/9/10 11:48 AM
 */
public interface BasicUserInfoPOExtMapper extends BasicUserInfoPOMapper {

    List<BasicUserInfoPO> listBasicUserInfo();
}
