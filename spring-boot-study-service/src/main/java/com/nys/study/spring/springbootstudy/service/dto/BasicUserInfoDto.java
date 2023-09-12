package com.nys.study.spring.springbootstudy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 用户信息
 * @date 2023/9/7 7:36 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicUserInfoDto {

    private Long id;

    /**
     * 服务器
     */
    private String server;

    private String app;

    private String userName;

    private String password;

    private String remark;

}
