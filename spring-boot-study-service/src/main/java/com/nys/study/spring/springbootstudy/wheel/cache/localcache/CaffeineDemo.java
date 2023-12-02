package com.nys.study.spring.springbootstudy.wheel.cache.localcache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.TimeUnit;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: Caffeine demo
 * @date 2023/9/5 5:37 PM
 */
public class CaffeineDemo implements InitializingBean {

    private Cache cache;

    @Override
    public void afterPropertiesSet() throws Exception {
        cache = Caffeine.newBuilder()
                //初始数量
                .initialCapacity(100)
                //最大条数
                .maximumSize(1000)
                //PS：expireAfterWrite和expireAfterAccess同时存在时，以expireAfterWrite为准。
                //最后一次写操作后经过指定时间过期
                .expireAfterWrite(1, TimeUnit.SECONDS)
                //最后一次读或写操作后经过指定时间过期
                .expireAfterAccess(1, TimeUnit.SECONDS)
                //监听缓存被移除
                .removalListener((key, val, removalCause) -> { })
                //记录命中
                .recordStats()
                .build();
    }
}
