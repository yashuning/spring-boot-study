package com.nys.study.spring.springbootstudy.web.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nys.study.spring.springbootstudy.web.annotation.ResponseIntercept;
import com.nys.study.spring.springbootstudy.web.annotation.ResponseNotIntercept;
import com.nys.study.spring.springbootstudy.web.vo.base.ResponseResultVO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 使用 @RestControllerAdvice 拦截Controller方法默认返回参数，统一处理返回值/响应体
 * @date 2023/9/22 5:43 PM
 */
@RestControllerAdvice
public class CommonResponseResult implements ResponseBodyAdvice<Object> {
    /**
     * 说明：@RestControllerAdvice：是一个组合注解，包含@ControllerAdvice和@ResponseBody。 实现ResponseBodyAdvice接口 需要重写supports,beforeBodyWrite方法
     * 作用一般是用于拦截Controller方法的返回值，统一处理返回值/响应体， 加解密，签名等
     */

    @Autowired
    private ObjectMapper objectMapper;

    private static final Class<? extends Annotation> ANNOTATION_TYPE_RESPONSE_NOT_INTERCEPT = ResponseNotIntercept.class;

    private static final Class<? extends Annotation> ANNOTATION_TYPE_RESPONSE_INTERCEPT = ResponseIntercept.class;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
//        if (returnType.getDeclaringClass().isAnnotationPresent(ResponseNotIntercept.class)) {
//            //若在类中加了@ResponseNotIntercept 则该类中的方法不用做统一的拦截
//            return false;
//        }
//        if (Objects.requireNonNull(returnType.getMethod()).isAnnotationPresent(ResponseNotIntercept.class)) {
//            //若方法上加了@ResponseNotIntercept 则该方法不用做统一的拦截
//            return false;
//        }

//        if (returnType.getMethod().isAnnotationPresent(ResponseIntercept.class)) {
//            //若方法上加了@ResponseNotIntercept 则该方法不用做统一的拦截
//            return true;
//        }
        if (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE_RESPONSE_NOT_INTERCEPT)
                || returnType.hasMethodAnnotation(ANNOTATION_TYPE_RESPONSE_NOT_INTERCEPT)) {
            return false;
        }
        if (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE_RESPONSE_INTERCEPT)
                || returnType.hasMethodAnnotation(ANNOTATION_TYPE_RESPONSE_INTERCEPT)) {
            return true;
        }
        // 若返回结果为true,则调用beforeBodyWrite方法
        return false;
    }

    /**
     * 在选择HttpMessageConverter之后以及在调用其write方法之前调用。
     */
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (body instanceof ResponseResultVO) {
            // 提供一定的灵活度，如果body已经被包装了，就不进行包装
            return body;
        }
        if (body instanceof String) {
            //解决返回值为字符串时，不能正常包装 如果返回类型是string，那么springmvc是直接返回的，此时需要手动转化为json
            return objectMapper.writeValueAsString(ResponseResultVO.success(body));
        }
        return ResponseResultVO.success(body);
    }
}
