package com.nys.study.spring.springbootstudy.wheel.kafka.juejin.serializer;

import com.nys.study.spring.springbootstudy.wheel.kafka.juejin.Company;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.core.GenericTypeResolver;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: protostuff 反序列化
 * @date 2023/12/8 17:36
 */
public class ProtoCommonDeserializer<T> implements Deserializer<T> {

    @SneakyThrows
    @Override
    public T deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        Class<T> tClazz = (Class<T>) GenericTypeResolver.resolveTypeArgument(this.getClass(), ProtoCommonDeserializer.class);
        Schema<T> schema = RuntimeSchema.getSchema(tClazz);
        T t = tClazz.newInstance();
        ProtostuffIOUtil.mergeFrom(data, t, schema);
        return t;
    }
}
