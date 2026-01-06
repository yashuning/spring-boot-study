package com.nys.study.spring.springbootstudy.wheel.kafka.juejin.serializer;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.core.GenericTypeResolver;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: protostuff 序列化
 * @date 2023/12/8 17:35
 */
public class ProtoCommonSerializer<T> implements Serializer<T> {

    @Override
    public byte[] serialize(String topic, T data) {
        if (data == null) {
            return null;
        }
        Schema<T> schema = (Schema<T>) RuntimeSchema.getSchema(data.getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        return ProtostuffIOUtil.toByteArray(data, schema, buffer);
    }
}
