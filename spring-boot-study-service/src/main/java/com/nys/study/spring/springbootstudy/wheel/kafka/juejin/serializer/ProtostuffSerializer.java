package com.nys.study.spring.springbootstudy.wheel.kafka.juejin.serializer;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: protostuff 序列化
 * @date 2023/12/8 17:35
 */
public class ProtostuffSerializer implements Serializer {

    @Override
    public byte[] serialize(String topic, Object data) {
        if (data == null) {
            return null;
        }
        Schema schema = (Schema) RuntimeSchema.getSchema(data.getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        byte[] protostuff = null;
        try {
            protostuff = ProtostuffIOUtil.toByteArray(data, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
        return protostuff;
    }
}
