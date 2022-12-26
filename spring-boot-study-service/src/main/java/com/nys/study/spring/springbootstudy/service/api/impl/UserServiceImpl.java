package com.nys.study.spring.springbootstudy.service.api.impl;

import com.nys.study.spring.springbootstudy.common.util.BeanUtil;
import com.nys.study.spring.springbootstudy.repository.mysql.dao.UserMapper;
import com.nys.study.spring.springbootstudy.repository.mysql.entity.UserEntity;
import com.nys.study.spring.springbootstudy.service.api.UserService;
import com.nys.study.spring.springbootstudy.service.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: UserService
 * @date 2022/12/26 8:28 下午
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 获取所有用户信息
     *
     * @return
     */
    @Override
    public List<UserDto> listUserInfo() {
        List<UserEntity> userEntityList = userMapper.listUserInfo();
        return BeanUtil.copyList(userEntityList, UserDto::new);
    }
}
