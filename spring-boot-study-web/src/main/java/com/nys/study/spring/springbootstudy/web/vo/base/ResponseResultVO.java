package com.nys.study.spring.springbootstudy.web.vo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: Controller层返回结果封装
 * @date 2023/9/22 4:15 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResultVO<T> {
    /**
     * 成功失败 1-成功；0-失败
     */
    private Integer code;

    /**
     * 返回信息描述
     */
    private String message;

    /**
     * 返回成功时的结果集
     */
    private T data;

    public static <T> ResponseResultVO<T> success(T data) {
        return new ResponseResultVO<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    public static <T> ResponseResultVO<T> success(String message, T data) {
        return new ResponseResultVO<>(ResultEnum.SUCCESS.getCode(), message, data);
    }

    public static ResponseResultVO<?> failed() {
        return new ResponseResultVO<>(ResultEnum.COMMON_FAILED.getCode(), ResultEnum.COMMON_FAILED.getMessage(), null);
    }

    public static ResponseResultVO<?> failed(String message) {
        return new ResponseResultVO<>(ResultEnum.COMMON_FAILED.getCode(), message, null);
    }

    public static ResponseResultVO<?> failed(ResultEnum errorResult) {
        return new ResponseResultVO<>(errorResult.getCode(), errorResult.getMessage(), null);
    }

    public static ResponseResultVO<?> failed(Integer code, String message) {
        return new ResponseResultVO<>(code, message, null);
    }

    public static <T> ResponseResultVO<T> instance(Integer code, String message, T data) {
        ResponseResultVO<T> result = new ResponseResultVO<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
