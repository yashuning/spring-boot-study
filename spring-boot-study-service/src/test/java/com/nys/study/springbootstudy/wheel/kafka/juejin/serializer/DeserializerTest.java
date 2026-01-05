package com.nys.study.springbootstudy.wheel.kafka.juejin.serializer;


import com.nys.study.spring.springbootstudy.wheel.kafka.juejin.Company;
import com.nys.study.spring.springbootstudy.wheel.kafka.juejin.serializer.ProtoCommonDeserializer;
import com.nys.study.spring.springbootstudy.wheel.kafka.juejin.serializer.ProtostuffDeserializer;
import com.nys.study.spring.springbootstudy.wheel.kafka.juejin.serializer.ProtostuffSerializer;
import org.junit.Test;

public class DeserializerTest {

    @Test
    public void testDeserialize() {
        String topic = "abc";
        Company company = new Company("wangchunsheng", "not same place");
        ProtostuffSerializer protostuffSerializer = new ProtostuffSerializer();
        byte[] data = protostuffSerializer.serialize(topic, company);
        ProtostuffDeserializer protostuffDeserializer = new ProtostuffDeserializer();
        /**
         * 注意此处的写法，最后有个花括号，也就是定义了一个ProtoCommonDeserializer<Company>的子类，同时这个子类是个匿名类
         * protoCommonDeserializer 指向的是这个匿名类的对象
          */
        ProtoCommonDeserializer<Company> protoCommonDeserializer = new ProtoCommonDeserializer<Company>(){};
        Company company1 = protostuffDeserializer.deserialize(topic, data);
        Company company2 = protoCommonDeserializer.deserialize(topic, data);
        assert company1.equals(company2);
    }

}
