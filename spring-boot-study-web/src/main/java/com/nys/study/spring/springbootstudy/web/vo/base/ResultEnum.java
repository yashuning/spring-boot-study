package com.nys.study.spring.springbootstudy.web.vo.base;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: Controller层返回结果状态码
 * @date 2023/9/22 4:19 PM
 */
public enum ResultEnum {

    SUCCESS(200, "成功"),
    /* 参数错误 */
    PARAM_IS_INVALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    COMMON_FAILED(500, "系统错误"),
    FORBIDDEN(2004, "没有权限访问资源");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
