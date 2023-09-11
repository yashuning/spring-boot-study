package com.nys.study.spring.springbootstudy.cache.localcache.hashmapcache;

import java.util.Random;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 可能失败的场景
 * @date 2023/9/5 4:12 PM
 */
public class MayFailCompute implements ComputeAble<String, Integer>{

    /**
     * 触发失败阈值
     */
    private static final int FAILURE_THRESHOLD = 50;

    /**
     * 随机数生成器
     */
    private static final Random RANDOM = new Random(100);

    /**
     * 有可能会出现失败的计算方法
     * @param arg 入参
     * @return java.lang.Integer
     */
    @Override
    public Integer doCompute(String arg) throws Exception {
        /*
         * 制造缓存污染场景，命中率降低
         * 缓存污染，是指系统将不常用的数据从内存移到缓存，造成常用数据的挤出，降低了缓存效率的现象。
         */
        if(RANDOM.nextInt() < FAILURE_THRESHOLD){
            throw new Exception("自定义异常");
        }
        return Integer.parseInt(arg);
    }
}
