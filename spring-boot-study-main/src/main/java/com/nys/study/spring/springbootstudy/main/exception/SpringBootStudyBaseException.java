package com.nys.study.spring.springbootstudy.main.exception;

import com.nys.study.spring.springbootstudy.common.enums.ErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 基础异常
 * @date 2022/12/23 2:42 下午
 */
@Getter
@Setter
public class SpringBootStudyBaseException extends RuntimeException {

    private Long code;

    public SpringBootStudyBaseException(ErrorCodeEnum errorCode, String message, Throwable cause) {
        super(message, cause);
        this.code = errorCode.getCode();
    }
}
