package com.nys.study.spring.springbootstudy.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;

@Slf4j
public class JsonTool {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        //序列化时，跳过null属性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //序列化时，遇到空bean（无属性）时不会失败
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //反序列化时，遇到未知属性（在bean上找不到对应属性）时不会失败
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //反序列化时，将空数组([])当做null来处理（以便把空数组反序列化到对象属性上——对php生成的json的map属性很有用）
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        //不通过fields来探测（仅通过标准getter探测）
        mapper.configure(MapperFeature.AUTO_DETECT_FIELDS, false);
    }


    /**
     * Json串转为对象
     */
    public static <T> T parse(String json, Class<?> parametrized, Class<?>... parameterClasses) throws JsonProcessingException {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JavaType jsonType = new ObjectMapper().getTypeFactory().constructParametricType(parametrized, parameterClasses);
        return mapper.readValue(json, jsonType);
    }

    /**
     * Json串转为对象
     */
    public static <T> T parse(String json, JavaType type) throws JsonProcessingException {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return mapper.readValue(json, type);
    }

    /**
     * Json串转为对象
     */
    public static <T> T parse(String json, TypeReference<T> type) throws JsonProcessingException {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return mapper.readValue(json, type);
    }

    /**
     * Json串转为对象
     */
    public static <T> T parse(String json, Class<T> type) throws JsonProcessingException {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return mapper.readValue(json, type);
    }

    /**
     * 输入流转为对象
     */
    public static <T> T parse(InputStream stream, TypeReference<T> type) throws IOException {
        return mapper.readValue(stream, type);
    }

    /**
     * 输入流转为对象
     */
    public static <T> T parse(InputStream stream, Class<T> type) throws IOException {
        return mapper.readValue(stream, type);
    }

    /* ====================== 序列化工具 ==================== */

    /**
     * 序列化对象转为json-string
     */
    public static String writeToString(Object target) throws JsonProcessingException {
        return mapper.writeValueAsString(target);
    }

    /**
     * 序列化对象并写入Writer
     */
    public static void write(Writer writer, Object target) throws IOException {
        mapper.writeValue(writer, target);
    }

    /**
     * 序列化对象并写入Stream
     */
    public static void write(OutputStream stream, Object target) throws IOException {
        mapper.writeValue(stream, target);
    }

    public static Map objectToMap(Object object) {
        return mapper.convertValue(object, Map.class);
    }


}
