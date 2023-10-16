package com.nys.study.spring.springbootstudy.main;

import com.nys.study.spring.springbootstudy.dao.mysql.mapper.BasicUserInfoPOExtMapper;
import com.nys.study.spring.springbootstudy.repository.IBasicUserInfoRepository;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 测试基类
 * @date 2022/12/9 4:00 下午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootStudyApplication.class)
public class BaseTest {

    @MockBean(name = "basicUserInfoPOExtMapper")
    protected BasicUserInfoPOExtMapper basicUserInfoPOExtMapper;

}
