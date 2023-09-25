package com.nys.study.spring.springbootstudy.common.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 错误码枚举
 * @date 2022/12/23 2:48 下午
 */
@Getter
public enum ErrorCodeEnum {

    SUCCESS(0L, "成功"),
    SERVER_ERROR(1L, "服务异常"),
    BUSINESS_ERROR(2L, "业务异常"),
    JSON_PARSE_ERROR(1005L, "json parse异常");

    private Long code;
    private String msg;

    ErrorCodeEnum(Long code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
