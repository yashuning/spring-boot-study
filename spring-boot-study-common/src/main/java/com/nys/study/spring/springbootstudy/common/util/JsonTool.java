package com.nys.study.spring.springbootstudy.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Slf4j
public class JsonTool {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        //序列化时，跳过null属性
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //序列化时，遇到空bean（无属性）时不会失败
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //反序列化时，遇到未知属性（在bean上找不到对应属性）时不会失败
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //反序列化时，将空数组([])当做null来处理（以便把空数组反序列化到对象属性上——对php生成的json的map属性很有用）
        MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        //不通过fields来探测（仅通过标准getter探测）
        MAPPER.configure(MapperFeature.AUTO_DETECT_FIELDS, false);
    }


    /**
     * Json串转为对象
     */
    public static <T> T toObject(String json, Class<?> parametrized, Class<?>... parameterClasses) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JavaType jsonType = new ObjectMapper().getTypeFactory().constructParametricType(parametrized, parameterClasses);
        try {
            return MAPPER.readValue(json, jsonType);
        } catch (Exception e) {
            log.error("json parse object error！", e);
            return null;
        }
    }

    /**
     * Json串转为对象
     */
    public static <T> T toObject(String json, JavaType type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return MAPPER.readValue(json, type);
        } catch (Exception e) {
            log.error("json parse object error！", e);
            return null;
        }
    }

    /**
     * Json串转为对象
     */
    public static <T> T toObject(String json, TypeReference<T> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return MAPPER.readValue(json, type);
        } catch (Exception e) {
            log.error("json parse object error！", e);
            return null;
        }
    }

    /**
     * Json串转为对象
     */
    public static <T> T toObject(String json, Class<T> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return MAPPER.readValue(json, type);
        } catch (Exception e) {
            log.error("json parse object error！", e);
            return null;
        }
    }

    /* ====================== 序列化工具 ==================== */

    /**
     * 序列化对象转为json-string
     */
    public static String toJsonString(Object target) {
        if (Objects.isNull(target)) {
            return StringUtils.EMPTY;
        }
        try {
            return MAPPER.writeValueAsString(target);
        } catch (Exception e) {
            log.error("object parse jsonstring error！", e);
            return StringUtils.EMPTY;
        }
    }


}
