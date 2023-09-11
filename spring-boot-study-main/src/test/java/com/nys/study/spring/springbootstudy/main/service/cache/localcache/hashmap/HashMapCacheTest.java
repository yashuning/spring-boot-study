package com.nys.study.spring.springbootstudy.main.service.cache.localcache.hashmap;

import com.nys.study.spring.springbootstudy.main.BaseTest;
import com.nys.study.spring.springbootstudy.cache.localcache.hashmapcache.HashMapCacheV1;
import com.nys.study.spring.springbootstudy.cache.localcache.hashmapcache.HashMapCacheV2;
import com.nys.study.spring.springbootstudy.cache.localcache.hashmapcache.HashMapCacheV3;
import com.nys.study.spring.springbootstudy.cache.localcache.hashmapcache.HashMapCacheV5;
import com.nys.study.spring.springbootstudy.cache.localcache.hashmapcache.HashMapCacheV6;
import com.nys.study.spring.springbootstudy.thread.ThreadSafeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: HashMap作为本地缓存测试
 * @date 2023/9/5 3:03 PM
 */
@Slf4j
public class HashMapCacheTest extends BaseTest {
    @Test
    public void testHashMapCacheV1() {
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        HashMapCacheV1 hashMapCacheV1 = new HashMapCacheV1();
        Random random = new Random(100);
        for (int i = 0; i < 500; i++) {
            executorService.execute(() -> {
                int randomInt = random.nextInt(100);
                try {
                    hashMapCacheV1.compute(String.valueOf(randomInt));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });
        }
        executorService.shutdown();
    }

    @Test
    public void testHashMapCacheV2() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        HashMapCacheV2 hashMapCacheV2 = new HashMapCacheV2();
        Random random = new Random(100);
        for (int i = 0; i < 500; i++) {
            executorService.execute(() -> {
                int randomInt = random.nextInt(100);
                try {
                    hashMapCacheV2.compute(String.valueOf(randomInt));
                } catch (Exception e) {
                    log.error("错误", e);
                    throw new RuntimeException(e);
                }

            });
        }
        executorService.shutdown();
    }

    @Test
    public void testHashMapCacheV3() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        HashMapCacheV3 hashMapCacheV3 = new HashMapCacheV3();
        Random random = new Random(100);
        for (int i = 0; i < 500; i++) {
            executorService.execute(() -> {
                int randomInt = random.nextInt(100);
                try {
                    hashMapCacheV3.compute(String.valueOf(randomInt));
                } catch (Exception e) {
                    log.error("错误", e);
                    throw new RuntimeException(e);
                }

            });
        }
        executorService.shutdown();
    }

    @Test
    public void testHashMapCacheV5() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        HashMapCacheV5<String, Integer> hashMapCacheV5 = new HashMapCacheV5<>();
        Random random = new Random(100);
        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                int randomInt = random.nextInt(100);
                Integer user = hashMapCacheV5.compute(String.valueOf(randomInt));
                System.out.println("result => " + user);


            });
        }
        executorService.shutdown();
    }

    @Test
    public void testHashMapCacheV6() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        HashMapCacheV6<String, Integer> hashMapCacheV6 = new HashMapCacheV6<>();
        Random random = new Random(100);
        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                int randomInt = random.nextInt(100);

                Integer user = hashMapCacheV6.compute(String.valueOf(randomInt));
                System.out.println("result => " + user);


            });
        }
        executorService.shutdown();
    }

    /**
     * 性能测试
     *
     * @throws Exception 所有异常
     */
    @Test
    public void testHashMapCachePerformance() throws Exception {
        long beginTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        HashMapCacheV6<String, Integer> hashMapCacheV6 = new HashMapCacheV6<>();
        Random random = new Random(200);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                int randomInt = random.nextInt(100);

                try {
                    countDownLatch.await();
                    // 从线程安全的集合当中取出当前时间
                    SimpleDateFormat simpleDateFormat = ThreadSafeFormatter.dateFormatter.get();
                    System.out.println("simpleDateFormat => " + simpleDateFormat.format(new Date()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Integer user = hashMapCacheV6.compute(String.valueOf(randomInt), 5000);
                System.out.println("result => " + user);
            });
        }
        // 假设此时所有的请求需要5秒时间准备。
        Thread.sleep(1000);
        countDownLatch.countDown();
        executorService.shutdown();
        long endTime = System.currentTimeMillis();
        // 如果线程池没有停止一直死循环
        while (!executorService.isTerminated()) {

        }
        System.out.println("最终时间" + (endTime - beginTime));
    }

}
