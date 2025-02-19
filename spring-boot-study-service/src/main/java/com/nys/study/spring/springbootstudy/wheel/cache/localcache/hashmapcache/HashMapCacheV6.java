package com.nys.study.spring.springbootstudy.wheel.cache.localcache.hashmapcache;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author nys
 * 高性能缓存第六版
 */
@Slf4j
public class HashMapCacheV6<K, V> {
    private final Map<K, Future<V>> cache = Maps.newConcurrentMap();
    private static final Random RANDOM = new Random();

    private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = new ScheduledThreadPoolExecutor(10);

    private final ComputeAble computeAble = new MayFailCompute();


    public V compute(K arg) {
        return doCompute(arg);
    }

    /**
     * 方法好像只做了定时清理缓存，但是没有把对象放到缓存里
     */
    public V compute(K arg, long expireTime) {
        if (expireTime <= 0) {
            return doCompute(arg);
        }
        SCHEDULED_EXECUTOR_SERVICE.schedule(new Runnable() {
            @Override
            public void run() {
                // 定期清除缓存的方法
                expire(arg);
            }

            /*
             * 注意需要同步方法，防止多线程重复添加定时任务
             * 是不是可以用双重检查锁来减小锁粒度？
             */
            private synchronized void expire(K arg) {
                // 检查当前 key 是否存在
                Future<V> vFuture = cache.get(arg);
                // 如果 value 存在，则需要进行
                if (Objects.nonNull(vFuture)) {
                    //如果任务被取消，此时需要关闭对应的定时任务
                    if (vFuture.isDone()) {
                        log.warn("future 任务被取消");
                        vFuture.cancel(true);
                    }
                    log.warn("过期时间到了，缓存被清除");
                    cache.remove(arg);
                }
            }
        }, expireTime, TimeUnit.MILLISECONDS);
        return doCompute(arg);
    }

    public V compute(K arg, long expireTime, boolean isRandom) {
        if (isRandom) {
            return compute(arg, expireTime);
        } else {
            return compute(arg, expireTime + RANDOM.nextInt(1000));
        }
    }


    private V doCompute(K arg) {
        // 对于重复计算进行处理
        while (true) {
            Future<V> result = cache.get(arg);
            try {
                // 如果获取不到内容，说明不在缓存当中
                if (Objects.isNull(result)) {
                    // 此时利用callAble 线程任务指定任务获取，在获取到结果之前线程会阻塞
                    FutureTask<V> future = new FutureTask<>(new Callable<V>() {
                        @Override
                        @SuppressWarnings("unchecked")
                        public V call() throws Exception {
                            return (V) computeAble.doCompute(arg);

                        }
                    });
                    //把新的future覆盖之前获取的future
                    result = future;
                    // 执行
                    future.run();
                    System.out.println("FutureTask 调用计算函数");
                    result = cache.putIfAbsent(arg, result);
                    // 如果返回null，说明这个记录被添加过了
                    if (Objects.isNull(result)) {
                        System.out.println("其他线程进行设置,重新执行计算");
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
            } catch (CancellationException cancellationException) {
                log.warn("CancellationException result => {}", result);
                // 线程在执行过程当中有可能被取消
                // 被取消的时候不管如何处理，首先需要先从缓存中移除掉污染缓存
                cache.remove(arg);
                throw new RuntimeException(cancellationException);
            } catch (InterruptedException e) {
                log.warn("InterruptedException result => {}", result);
                // 线程被中断的异常处理
                cache.remove(arg);
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
//            log.warn("ExecutionException result => {}", result);
                log.info("移除缓存Key => {}，重新计算", arg);
                cache.remove(arg);
                // 不会抛出异常，而是重新在下一次循环中计算
//            throw new RuntimeException(e);
            } catch (Exception e) {
                log.warn("Exception result => {}", result);
                cache.remove(arg);
                // 无法处理的未知异常，直接抛出运行时异常不做任何处理。
                throw new RuntimeException(e);
            }

        }
    }
}
