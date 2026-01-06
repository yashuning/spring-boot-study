package com.nys.study.spring.springbootstudy.wheel.kafka.juejin.serializer;

import com.nys.study.spring.springbootstudy.wheel.kafka.juejin.DomainWrapper;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: protostuff 反序列化
 * @date 2023/12/8 17:36
 */
public class ProtoWrapperDeserializer implements Deserializer<DomainWrapper> {


    @Override
    public DomainWrapper deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        Schema<DomainWrapper> schema = RuntimeSchema.getSchema(DomainWrapper.class);
        DomainWrapper ans = new DomainWrapper();
        ProtostuffIOUtil.mergeFrom(data, ans, schema);
        return ans;
    }
}
