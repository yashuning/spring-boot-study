package com.nys.study.spring.springbootstudy.main.service;

import com.nys.study.spring.springbootstudy.common.util.JsonTool;
import com.nys.study.spring.springbootstudy.main.BaseTest;
import com.nys.study.spring.springbootstudy.service.api.UserService;
import com.nys.study.spring.springbootstudy.service.dto.UserDto;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: User测试
 * @date 2022/12/26 8:44 下午
 */
public class UserServiceTest extends BaseTest {

    @Resource
    private UserService userService;

    @Test
    public void testListUserInfo(){
        List<UserDto> userDtoList = userService.listUserInfo();
        System.out.println(JsonTool.toJsonString(userDtoList));
    }

}
