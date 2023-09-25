package com.nys.study.spring.springbootstudy.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 在类和方法上使用此注解表示不会在ResponseResult类中进一步封装返回值，直接返回原生值
 * @date 2023/9/22 5:51 PM
 */
@Target({ElementType.METHOD, ElementType.TYPE})  //可以在字段、方法
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseNotIntercept {
    String value() default "";
}
