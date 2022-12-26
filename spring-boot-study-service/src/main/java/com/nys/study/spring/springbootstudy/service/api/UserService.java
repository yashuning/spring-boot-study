package com.nys.study.spring.springbootstudy.service.api;

import com.nys.study.spring.springbootstudy.service.dto.UserDto;

import java.util.List;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: UserService
 * @date 2022/12/26 8:28 下午
 */
public interface UserService {
    List<UserDto> listUserInfo();
}
