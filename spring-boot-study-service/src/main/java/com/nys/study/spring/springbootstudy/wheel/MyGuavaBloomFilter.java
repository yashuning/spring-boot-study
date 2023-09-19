package com.nys.study.spring.springbootstudy.wheel;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 布隆过滤器使用
 * @date 2023/9/13 3:16 PM
 */
@Slf4j
public class MyGuavaBloomFilter implements InitializingBean {

    /**
     * 预计插入的数据
     */
    private static Integer expectedInsertions = 10000000;
    /**
     * 误判率
     */
    private static Double FPP = 0.01;
    /**
     * 布隆过滤器
     */
    private static BloomFilter<Integer> bloomFilterInteger;

    private static BloomFilter<String> bloomFilterString;

    @Override
    public void afterPropertiesSet() throws Exception {
//        bloomFilterInteger = BloomFilter.create(Funnels.integerFunnel(), expectedInsertions, FPP);
//        // 插入 1千万数据
//        for (int i = 0; i < expectedInsertions; i++) {
//            bloomFilterInteger.put(i);
//        }
//
//        // 从10000000开始，用1千万数据测试误判率
//        int count = 0;
//        for (int i = expectedInsertions; i < expectedInsertions * 2; i++) {
//            if (bloomFilterInteger.mightContain(i)) {
//                count++;
//            }
//        }
//        System.out.println("一共误判了：" + count);
    }

    private void bloomFilterStringTest(boolean useFpp){
        int size = 10000;
        double fpp = 0.0001;

        //没有设置误判率的情况下，10000→312，误判率3.12%
        if (useFpp){
            bloomFilterString = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 10000, fpp);
        }else {
            bloomFilterString = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 10000);
        }
        for (int m = 0; m < size; m++) {
            bloomFilterString.put("" + m);
        }
        List<Integer> list = new ArrayList<Integer>();
        for (int n = size + 10000; n < size + 20000; n++) {
            if (bloomFilterString.mightContain("" + n)) {
                list.add(n);
            }
        }
        System.out.println("误判数量：" + list.size());
        log.info("误判数量=%s", list.size());
    }

    public static void main(String[] args) {
        MyGuavaBloomFilter myGuavaBloomFilter = new MyGuavaBloomFilter();
        myGuavaBloomFilter.bloomFilterStringTest(false);
        myGuavaBloomFilter.bloomFilterStringTest(true);
    }
}
