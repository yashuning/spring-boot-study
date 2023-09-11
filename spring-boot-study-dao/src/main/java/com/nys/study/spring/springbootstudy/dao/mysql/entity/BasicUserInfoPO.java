package com.nys.study.spring.springbootstudy.dao.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2023/09/08
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicUserInfoPO {
    /**
     * 字段: id
     * 说明: 
     */
    private Long id;

    /**
     * 字段: app
     * 说明: 
     */
    private String app;

    /**
     * 字段: user_name
     * 说明: 用户名
     */
    private String userName;

    /**
     * 字段: password
     * 说明: 密码
     */
    private String password;

    /**
     * 字段: remark
     * 说明: 备注
     */
    private String remark;
}