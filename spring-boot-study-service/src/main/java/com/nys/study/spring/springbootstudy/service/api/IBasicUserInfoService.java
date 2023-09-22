package com.nys.study.spring.springbootstudy.service.api;

import com.nys.study.spring.springbootstudy.dto.BasicUserInfoDto;

import java.util.List;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: TODO
 * @date 2023/9/10 12:11 PM
 */
public interface IBasicUserInfoService {
    List<BasicUserInfoDto> listBasicUserInfo();
}
