package com.nys.study.spring.springbootstudy.repository;

import com.nys.study.spring.springbootstudy.dto.BasicUserInfoDto;

import java.util.List;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 用户信息
 * @date 2023/9/10 11:44 AM
 */
public interface IBasicUserInfoRepository {

    List<BasicUserInfoDto> listBasicUserInfo();
}
