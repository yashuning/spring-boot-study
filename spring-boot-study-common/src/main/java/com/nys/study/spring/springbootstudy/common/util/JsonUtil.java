package com.nys.study.spring.springbootstudy.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: json序列化
 * @date 2023/9/24 10:29 AM
 */
public class JsonUtil {
    private JsonUtil() {
    }

    private static final Logger logger = LogManager.getLogger(JsonUtil.class);
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

    /* ====================== 反序列化工具 ==================== */

    /**
     * Json串转为对象
     */
    public static <T> T parse(String json, TypeReference<T> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            logger.warn("parse failed. json={} type={} msg={}",
                    json, type.getType(), e.getMessage());
            return null;
        }
    }

    /**
     * Json串转为对象
     */
    public static <T> T parse(String json, Class<T> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            logger.warn("parse failed. json={} type={} msg={}",
                    json, type, e.getMessage());
            return null;
        }
    }

    /**
     * Json串转为对象
     */
    public static <T> T parseThrow(String json, Class<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            logger.warn("parse failed. json={} type={} msg={}",
                    json, type, e.getMessage());
            throw new IllegalArgumentException("json转换失败", e);
        }
    }

    /**
     * 输入流转为对象
     */
    public static <T> T parse(InputStream stream, TypeReference<T> type) {
        try {
            return mapper.readValue(stream, type);
        } catch (IOException e) {
            logger.warn("parse failed. type={} msg={}",
                    type.getType(), e.getMessage());
            return null;
        }
    }

    /**
     * 输入流转为对象
     */
    public static <T> T parse(InputStream stream, Class<T> type) {
        try {
            return mapper.readValue(stream, type);
        } catch (IOException e) {
            logger.warn("parse failed. type={} msg={}",
                    type, e.getMessage());
            return null;
        }
    }

    /* ====================== 序列化工具 ==================== */

    /**
     * 序列化对象转为json-string
     */
    public static String writeToString(Object target) {
        try {
            return mapper.writeValueAsString(target);
        } catch (JsonProcessingException e) {
            logger.warn("writeToString failed. target={} msg={}",
                    target.getClass(), e.getMessage());
            return StringUtils.EMPTY;
        }
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
}
