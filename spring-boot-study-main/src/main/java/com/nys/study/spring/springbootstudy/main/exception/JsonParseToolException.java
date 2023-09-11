package com.nys.study.spring.springbootstudy.main.exception;

import com.nys.study.spring.springbootstudy.common.enums.ErrorCodeEnum;
import lombok.Getter;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: json转换异常
 * @date 2022/12/23 2:41 下午
 */
@Getter
public class JsonParseToolException extends SpringBootStudyBaseException {

    public JsonParseToolException(String message, Throwable cause) {
        super(ErrorCodeEnum.JSON_PARSE_ERROR, message, cause);
    }
}
