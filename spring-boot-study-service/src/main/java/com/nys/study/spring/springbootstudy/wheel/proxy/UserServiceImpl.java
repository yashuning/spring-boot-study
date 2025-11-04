package com.nys.study.spring.springbootstudy.wheel.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: TODO
 * @date 2023/10/22 5:15 PM
 */
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public void select() {
        log.info("查询 selectById");
    }

    @Override
    public void update() {
        log.info("更新 update");
    }
}
