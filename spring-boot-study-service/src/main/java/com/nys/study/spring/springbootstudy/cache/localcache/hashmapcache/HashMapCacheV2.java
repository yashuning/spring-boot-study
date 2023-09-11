package com.nys.study.spring.springbootstudy.cache.localcache.hashmapcache;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author nys
 * <p>
 * 参考文章：https://mdnice.com/writing/8d66b9ac1f6b4c4ebd7062dcd632d021
 * <p>
 * 第二版，使用装饰模式进行改造
 * 为了避免线程不安全问题，synchronized 同步加锁
 */
@Slf4j
public class HashMapCacheV2 {
    /**
     * 缓存
     */
    private final Map<String, Integer> cache = Maps.newHashMap();

    /**
     * 计算方法实现对象
     */
    private final static ComputeAble<String, Integer> COMPUTE = new ExpensiveCompute();

    /**
     * 对缓存操作加锁 synchronized 防止并发问题
     *
     * @param userId 用户Id
     * @return 缓存数据
     * @throws Exception
     */
    public synchronized Integer compute(String userId) throws Exception {
        if (cache.containsKey(userId)) {
            log.info("cached => {}", userId);
            return cache.get(userId);
        }
        log.info("doCompute => {}", userId);
        Integer result = doCompute(userId);
        // 不存在缓存就加入
        cache.put(userId, result);
        return result;
    }

    /**
     * 计算方法由具体的类实现封装
     *
     * @param userId 用户Id
     * @return 计算后的值
     * @throws InterruptedException
     */
    private Integer doCompute(String userId) throws Exception {
        return COMPUTE.doCompute(userId);
    }
}
