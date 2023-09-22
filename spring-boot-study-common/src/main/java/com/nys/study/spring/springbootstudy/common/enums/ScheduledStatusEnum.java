package com.nys.study.spring.springbootstudy.common.enums;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 定时任务状态
 * @date 2023/9/21 9:25 PM
 */
public enum ScheduledStatusEnum {

    ENABLED(1, "正常"),
    DISABLED(2, "停用"),
    ;

    private Integer code;

    private String name;

    ScheduledStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Map<Integer, ScheduledStatusEnum> enumMap = Maps.newHashMap();

    static {
        Arrays.stream(ScheduledStatusEnum.values()).forEach(status -> enumMap.put(status.code, status));
    }

    public static ScheduledStatusEnum of(Integer code) {
        return enumMap.get(code);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
