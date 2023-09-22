package com.nys.study.spring.springbootstudy.dao.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: spring工具
 * @date 2023/9/21 7:59 PM
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.context = applicationContext;
    }

    public static <T> T getBean(Class<T> clz) {
        return context.getBean(clz);
    }

    public static Object getBean(String name) {
        return context.getBean(name);
    }

    public ApplicationContext getApplicationContext() {
        return context;
    }
}
