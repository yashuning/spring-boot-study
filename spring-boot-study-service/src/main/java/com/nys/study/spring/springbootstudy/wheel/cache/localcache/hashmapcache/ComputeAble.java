package com.nys.study.spring.springbootstudy.wheel.cache.localcache.hashmapcache;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 可计算接口 -- 装饰模式抽象计算业务
 * @date 2023/9/5 3:10 PM
 */
public interface ComputeAble<K, V> {
    /**
     * 根据指定参数 K 进行计算，计算结果为 V
     * @param arg 泛型参数
     * @return 返回计算后结果
     * @throws Exception
     */
    V doCompute(K arg) throws Exception;
}
