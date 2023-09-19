package com.nys.study.spring.springbootstudy.main.service;

import com.nys.study.spring.springbootstudy.common.util.JsonTool;
import com.nys.study.spring.springbootstudy.main.BaseTest;
import com.nys.study.spring.springbootstudy.service.api.IBasicUserInfoService;
import com.nys.study.spring.springbootstudy.service.dto.BasicUserInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: User测试
 * @date 2022/12/26 8:44 下午
 */
@Slf4j
public class UserServiceTest extends BaseTest {

    @Resource
    private IBasicUserInfoService basicUserInfoService;

    @Test
    public void testListUserInfo(){
        List<BasicUserInfoDto> userDtoList = basicUserInfoService.listBasicUserInfo();
        log.info("aaa111");
        System.out.println(JsonTool.toJsonString(userDtoList));
    }

}
