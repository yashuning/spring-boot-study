package com.nys.study.spring.springbootstudy.web.handler;

import com.nys.study.spring.springbootstudy.exception.BusinessException;
import com.nys.study.spring.springbootstudy.web.vo.base.ResponseResultUtil;
import com.nys.study.spring.springbootstudy.web.vo.base.ResponseResultVO;
import com.nys.study.spring.springbootstudy.web.vo.base.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: web层统一拦截异常
 * @date 2023/9/24 10:42 AM
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 顶级异常捕获并统一处理，当其他异常无法处理时候选择使用
     *
     * @ResponseStatus指定客户端收到的http状态码
     */
    @ExceptionHandler({Exception.class})
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "系统异常")
    @ResponseBody
    public ResponseResultVO<?> handle(Exception ex) {
        log.error("全局异常处理， errorMsg={}", ex.getMessage(), ex);
        return ResponseResultUtil.renderFailResult(ResultEnum.COMMON_FAILED, String.format("系统异常！errorMag=%s", ex.getMessage()));
    }

    /**
     * 捕获自定义异常
     */
    @ExceptionHandler({BusinessException.class})
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "系统业务处理异常")
    public ResponseResultVO<?> handleBusinessException(BusinessException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseResultUtil.renderFailResultByBusinessException(ex.getMessage());
    }

    /**
     * 参数缺失异常
     * 说明：参数为必填时，若入参中无此参数则会报MissingServletRequestParameterException
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "系统参数缺失异常")
    public ResponseResultVO<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error("全局异常处理-参数缺失，errorMsg={}", ex.getMessage(), ex);
        return ResponseResultUtil.renderFailResult(ResultEnum.PARAM_NOT_COMPLETE, String.format("系统参数缺失！errorMag=%s", ex.getMessage()));
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler({IllegalArgumentException.class})
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "系统参数校验异常")
    public ResponseResultVO<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("全局异常处理-参数校验失败，errorMsg={}", ex.getMessage(), ex);
        return ResponseResultUtil.renderFailResult(ResultEnum.PARAM_IS_INVALID, String.format("系统参数校验失败！errorMag=%s", ex.getMessage()));
    }
}
