package com.nys.study.spring.springbootstudy.repository.mysql.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserEntity implements Serializable {
    private Integer id;

    private String userName;

    private String password;

    private String desc;

    private static final long serialVersionUID = 1L;

    public UserEntity(Integer id, String userName, String password, String desc) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.desc = desc;
    }

}