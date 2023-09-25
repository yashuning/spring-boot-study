package com.nys.study.spring.springbootstudy.exception.base;

import com.nys.study.spring.springbootstudy.common.enums.ErrorCodeEnum;
import lombok.Getter;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 服务异常
 * @date 2023/9/24 10:48 AM
 */
@Getter
public class ServerErrorException extends RuntimeException {

    private Long code;

    public ServerErrorException(ErrorCodeEnum errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public ServerErrorException(ErrorCodeEnum errorCode, String message, Throwable cause) {
        super(message, cause);
        this.code = errorCode.getCode();
    }
}
