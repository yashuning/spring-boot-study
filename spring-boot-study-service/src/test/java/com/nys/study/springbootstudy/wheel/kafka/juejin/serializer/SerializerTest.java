package com.nys.study.springbootstudy.wheel.kafka.juejin.serializer;


import com.nys.study.spring.springbootstudy.wheel.kafka.juejin.Company;
import com.nys.study.spring.springbootstudy.wheel.kafka.juejin.User;
import com.nys.study.spring.springbootstudy.wheel.kafka.juejin.serializer.ProtoCommonDeserializer;
import com.nys.study.spring.springbootstudy.wheel.kafka.juejin.serializer.ProtoCommonSerializer;
import com.nys.study.spring.springbootstudy.wheel.kafka.juejin.serializer.ProtostuffDeserializer;
import com.nys.study.spring.springbootstudy.wheel.kafka.juejin.serializer.ProtostuffSerializer;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.Objects;

public class SerializerTest {

    @Test
    public void testSerialize() {
        String topic = "abc";
        Company company = new Company("wangchunsheng", "not same place");
        ProtostuffSerializer protostuffSerializer = new ProtostuffSerializer();
        ProtoCommonSerializer protoCommonSerializer = new ProtoCommonSerializer<>();
        byte[] serialize1 = protoCommonSerializer.serialize(topic, company);
        byte[] serialize2 = protostuffSerializer.serialize(topic, company);
        assert Objects.deepEquals(serialize1, serialize2);
    }

}
