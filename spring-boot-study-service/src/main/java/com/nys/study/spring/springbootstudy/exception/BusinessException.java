package com.nys.study.spring.springbootstudy.exception;

import com.nys.study.spring.springbootstudy.common.enums.ErrorCodeEnum;
import com.nys.study.spring.springbootstudy.exception.base.ServerErrorException;
import lombok.Getter;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 业务异常
 * @date 2023/9/24 10:44 AM
 */
@Getter
public class BusinessException extends ServerErrorException {

    public BusinessException(String message) {
        super(ErrorCodeEnum.BUSINESS_ERROR, message);
    }

    public BusinessException(String message, Throwable cause) {
        super(ErrorCodeEnum.BUSINESS_ERROR, message, cause);
    }
}
