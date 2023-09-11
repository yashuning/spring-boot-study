package com.nys.study.spring.springbootstudy.cache.localcache.hashmapcache;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author nys
 *
 * 参考文章：https://mdnice.com/writing/8d66b9ac1f6b4c4ebd7062dcd632d021
 *
 * 初版高速缓存实现
 *  1. 使用简单的HashMap
 *  2. 在高速缓存中进行计算
 *
 * 问题
 *  1. 复用性能查
 *  2. HashMap线程不安全，有可能重复判断
 */
@Slf4j
public class HashMapCacheV1 {

    // 本地缓存容器
    private final Map<String, Integer> cache = Maps.newHashMap();

    /**
     * 根据参数计算结果，此参数对于同一个参数会计算同样的结果
     * 1. 如果缓存存在结果，直接返回
     * 2. 如果缓存不存在，则需要计算添加到map之后才返回
     * @param userId 用户id
     * @return 缓存数据
     * @throws InterruptedException
     */
    public Integer compute(String userId) throws InterruptedException {
        if(cache.containsKey(userId)){
            log.info("cached => {}", userId);
            return cache.get(userId);
        }
        log.info("doCompute => {}", userId);
        Integer result = doCompute(userId);
        //不存在缓存就加入
        cache.put(userId, result);
        return result;
    }

    private Integer doCompute(String userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return Integer.parseInt(userId);
    }

}
