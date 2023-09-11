package com.nys.study.spring.springbootstudy.cache.localcache.hashmapcache;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author nys
 *  第五个版本，当碰到会抛出异常的计算方法的情况这时候应该重新计算
 *  对于不同的异常，也要对应不同的处理态度：
 *  - CancellationException 和 InterruptedException 基本都是人为操作，这时候应该立即终止任务。
 *  - 根据方法逻辑，我们可以知道方法是有可能计算成功的，只不过需要多重试几次。
 *  - while(true) 的加入可以让出错之后自动重新进行计算直到成功为止，但是如果是人为取消，就需要抛出异常并且结束。
 */
@Slf4j
public class HashMapCacheV5<K, V> {
    /**
     * 改造，并发不安全集合改为并发安全集合
     * value 存储为 future的值
     */
    private final Map<K, Future<V>> cache = Maps.newConcurrentMap();

    private final ComputeAble computeAble = new MayFailCompute();

    public V compute(K arg) {
        return doCompute(arg);
    }

    private V doCompute(K arg) {
        // 对于重复计算进行处理
        while (true) {
            Future<V> result = cache.get(arg);
            try {
                // 如果获取不到内容，说明不在缓存当中
                if (Objects.isNull(result)) {
                    // 此时利用callAble 线程任务指定任务获取，在获取到结果之前线程会阻塞
                    FutureTask<V> future = new FutureTask<>(() -> (V) computeAble.doCompute(arg));
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
