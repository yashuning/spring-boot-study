package mock.com.nys.study.spring.springbootstudy.main;

import com.nys.study.spring.springbootstudy.dao.mysql.mapper.BasicUserInfoPOExtMapper;
import com.nys.study.spring.springbootstudy.main.SpringBootStudyApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: mock测试基类
 * @date 2023/10/17 10:53 上午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootStudyApplication.class)
public class BaseMockTest {

    @MockBean(name = "basicUserInfoPOExtMapper")
    protected BasicUserInfoPOExtMapper basicUserInfoPOExtMapper;

}
