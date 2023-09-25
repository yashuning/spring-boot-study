package com.nys.study.spring.springbootstudy.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 返回结果拦截封装注解
 * @date 2023/9/22 5:28 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResponseIntercept {
}
