package com.nys.study.spring.springbootstudy.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: user
 * @date 2022/12/26 8:29 下午
 */
@Setter
@Getter
@ToString
public class UserDto {
    private Integer id;

    private String userName;

    private String password;

    private String desc;
}
