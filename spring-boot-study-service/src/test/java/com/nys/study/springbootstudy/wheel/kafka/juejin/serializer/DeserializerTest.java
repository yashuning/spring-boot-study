package com.nys.study.springbootstudy.wheel.kafka.juejin.serializer;


import com.nys.study.spring.springbootstudy.wheel.kafka.juejin.Company;
import com.nys.study.spring.springbootstudy.wheel.kafka.juejin.DomainWrapper;
import com.nys.study.spring.springbootstudy.wheel.kafka.juejin.User;
import com.nys.study.spring.springbootstudy.wheel.kafka.juejin.serializer.ProtoCommonDeserializer;
import com.nys.study.spring.springbootstudy.wheel.kafka.juejin.serializer.ProtoWrapperDeserializer;
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

    @Test
    public void testDeserialize1() {
        String topic = "abc";
        Company company = new Company("wangsansan", "not same place");
        ProtostuffSerializer protostuffSerializer = new ProtostuffSerializer();
        byte[] data = protostuffSerializer.serialize(topic, company);
        ProtostuffDeserializer protostuffDeserializer = new ProtostuffDeserializer();
        Company company1 = protostuffDeserializer.deserialize(topic, data);
        assert "wangsansan".equals(company1.getName());
    }

    @Test
    public void testWrapperDeserialize() {
        String topic = "abc";
        Company company = new Company("wangsansan", "not same place");
        User user = new User("wangsansan", 25);
        ProtostuffSerializer protostuffSerializer = new ProtostuffSerializer();
        byte[] data1 = protostuffSerializer.serialize(topic, DomainWrapper.of(company));
        byte[] data2 = protostuffSerializer.serialize(topic, DomainWrapper.of(user));
        ProtoWrapperDeserializer protoWrapperDeserializer = new ProtoWrapperDeserializer();
        DomainWrapper<Company> obj1 = protoWrapperDeserializer.deserialize(topic, data1);
        DomainWrapper<User> obj2 = protoWrapperDeserializer.deserialize(topic, data2);
        assert obj1.getDomain().getName().equals("wangsansan");
        assert obj2.getDomain().getAge() == 25;
    }

}
