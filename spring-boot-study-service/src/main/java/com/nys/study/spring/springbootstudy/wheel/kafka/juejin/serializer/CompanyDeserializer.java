package com.nys.study.spring.springbootstudy.wheel.kafka.juejin.serializer;

import com.nys.study.spring.springbootstudy.wheel.kafka.juejin.Company;
import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: CompanySerializer 自定义反序列化器
 * @date 2023/12/8 17:07
 */
public class CompanyDeserializer implements Deserializer<Company> {
    private static final String ENCODING = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Company deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        if (data.length < 8) {
            throw new SerializationException("Size of data received by DemoDeserializer is shorter than expected!");
        }
        ByteBuffer buffer = ByteBuffer.wrap(data);
        int nameLen, addressLen;
        String name, address;

        nameLen = buffer.getInt();
        byte[] nameBytes = new byte[nameLen];
        buffer.get(nameBytes);
        addressLen = buffer.getInt();
        byte[] addressBytes = new byte[addressLen];
        buffer.get(addressBytes);

        try {
            name = new String(nameBytes, ENCODING);
            address = new String(addressBytes, ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new SerializationException("Error occur when deserializing!");
        }

        return new Company(name, address);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}