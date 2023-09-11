package com.nys.study.spring.springbootstudy.cache.localcache.hashmapcache;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author nys
 * <p>
 * 参考文章：https://mdnice.com/writing/8d66b9ac1f6b4c4ebd7062dcd632d021
 * <p>
 * 第3版 优化多线程访问重复计算问题
 */
@Slf4j
public class HashMapCacheV4<K, V> {
    /**
     * 缓存
     */
    private final Map<K, Future<V>> cache = Maps.newConcurrentMap();

    /**
     * 计算方法实现对象
     */
    private final ComputeAble computeAble = new MayFailCompute();

    /**
     * 先使用具体类型实现，后续改为使用泛型实现
     * 1. 使用 FutureTask 对于要计算的值进行封装，根据 FutureTask 特性，获取到结果之前单个线程会一直等待
     * 2. 由于计算方法变动，所有的代码需要调整
     * 3. concurrentHashMap.get() 在 if 判断的时候依然存在非原子行为，所以在设置的时候使用 putIfAbsent 原子操作
     * 4. 重构，使用泛型参数
     *
     * @param arg
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public V compute(K arg) throws InterruptedException, ExecutionException {
        Future<V> result = cache.get(arg);
        // 如果获取不到内容，说明不在缓存当中
        if (Objects.isNull(result)) {
            // 此时利用callAble 线程任务指定任务获取，在获取到结果之前线程会阻塞
            FutureTask<V> future = new FutureTask<>(() -> (V) computeAble.doCompute(arg));
            //把新的future覆盖之前获取的future
            result = future;
            // 执行
            future.run();
            log.info("FutureTask 调用计算函数");
            result = cache.putIfAbsent(arg, result);
            // 如果返回null，说明这个记录被添加过了
            if (Objects.isNull(result)) {
                log.info("其他线程进行设置,重新执行计算");
                // 说明其他线程已经设置过值，这里重新跑一次计算方法即可直接获取
                result = future;
                // 再重新跑一次
                future.run();
                return result.get();
            } else {
                return result.get();
            }
        }
        return result.get();
    }

}
