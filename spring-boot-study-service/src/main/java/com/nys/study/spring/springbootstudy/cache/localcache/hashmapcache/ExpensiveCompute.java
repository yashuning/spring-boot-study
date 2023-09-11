package com.nys.study.spring.springbootstudy.cache.localcache.hashmapcache;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 装饰模式改写接口
 * @date 2023/9/5 3:12 PM
 */
@Slf4j
public class ExpensiveCompute implements ComputeAble<String, Integer> {
    @Override
    public Integer doCompute(String arg) throws Exception {
        TimeUnit.MILLISECONDS.sleep(20);
        return Integer.parseInt(arg);
    }
}
