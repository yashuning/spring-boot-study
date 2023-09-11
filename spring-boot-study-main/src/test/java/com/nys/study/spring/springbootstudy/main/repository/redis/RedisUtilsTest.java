package com.nys.study.spring.springbootstudy.main.repository.redis;

import com.nys.study.spring.springbootstudy.main.BaseTest;
import com.nys.study.spring.springbootstudy.dao.redis.RedisUtils;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: redis 工具测试
 * @date 2023/1/2 9:28 下午
 */
public class RedisUtilsTest extends BaseTest {

    @Resource
    private RedisUtils redisUtils;

    @Test
    public void setTest(){
        boolean setSuccess = redisUtils.set("a", "测试");
        assert setSuccess;
    }

    @Test
    public void getTest(){
        redisUtils.get("a");
    }
}
