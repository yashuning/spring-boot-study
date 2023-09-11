package com.nys.study.spring.springbootstudy.thread;

import java.text.SimpleDateFormat;

/**
 * @program: spring-boot-study
 * @Description: ThreadSafeFormatter
 * @date 2023/9/5 4:53 PM
 */
public class ThreadSafeFormatter {

    public static ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<SimpleDateFormat>() {

        /**
         * 每个线程会调用本方法一次，用于初始化
         * @param
         * @return java.text.SimpleDateFormat
         */
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("mm:ss");
        }

        /**
         * 首次调用本方法时，会调用initialValue()；后面的调用会返回第一次创建的值
         * @param
         * @return java.text.SimpleDateFormat
         */
        @Override
        public SimpleDateFormat get() {
            return super.get();
        }
    };

}
