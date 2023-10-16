package com.nys.study.spring.springbootstudy.main.service;

import com.nys.study.spring.springbootstudy.common.util.JsonTool;
import com.nys.study.spring.springbootstudy.main.BaseTest;
import com.nys.study.spring.springbootstudy.service.api.IBasicUserInfoService;
import com.nys.study.spring.springbootstudy.dto.BasicUserInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.annotation.Resource;
import java.util.Collections;
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

    @Before
    public void init() {
        BasicUserInfoDto userInfoDto = new BasicUserInfoDto();
        userInfoDto.setUserName("猪猪love");
        userInfoDto.setRemark("mock数据库查询");
        Mockito.doReturn(Collections.singletonList(userInfoDto))
                .when(basicUserInfoRepository)
                .listBasicUserInfo();
    }

    @Test
    public void testListUserInfo(){
        List<BasicUserInfoDto> userDtoList = basicUserInfoService.listBasicUserInfo();
        log.info("aaa111");
        System.out.println(JsonTool.toJsonString(userDtoList));
    }

}
