package com.nys.study.spring.springbootstudy.dao.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: redis 工具类
 * @date 2023/1/2 9:23 下午
 */
@Component
public class RedisUtils {

    /**
     * 后续改成注入RedisUtils对象，把方法都改成static的
     */

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    //- - - - - - - - - - - - - - - - - - - - -  String类型 - - - - - - - - - - - - - - - - - - - -

    /**
     * 根据key获取值
     * @param key
     * @return
     */
    public Object get(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true 成功 false 失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
