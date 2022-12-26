package com.nys.study.spring.springbootstudy.repository.mysql.dao;

import com.nys.study.spring.springbootstudy.repository.mysql.entity.UserEntity;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserEntity record);

    UserEntity selectByPrimaryKey(Integer id);

    List<UserEntity> listUserInfo();

    int updateByPrimaryKey(UserEntity record);
}