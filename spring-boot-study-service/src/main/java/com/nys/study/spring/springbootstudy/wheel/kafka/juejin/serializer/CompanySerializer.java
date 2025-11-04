package com.nys.study.spring.springbootstudy.wheel.kafka.juejin.serializer;

import com.nys.study.spring.springbootstudy.wheel.kafka.juejin.Company;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.common.serialization.Serializer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: Company 自定义序列化器
 * @date 2023/12/8 17:00
 */
@Slf4j
public class CompanySerializer implements Serializer<Company> {
    private static final String ENCODING = "UTF8";

    @Override
    public byte[] serialize(String topic, Company data) {
        if (data == null) {
            return null;
        }
        byte[] name, address;
        try {
            name = getStringBytes(data.getName());
            address = getStringBytes(data.getAddress());
            ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + name.length + address.length);
            buffer.putInt(name.length);
            buffer.put(name);
            buffer.putInt(address.length);
            buffer.put(address);
            return buffer.array();
        } catch (Exception e) {
            log.error("serializer -- 自定义company序列化器序列化异常！", e);
        }
        return new byte[0];
    }

    private byte[] getStringBytes(String value) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(value)) {
            return new byte[0];
        }
        return value.getBytes(ENCODING);
    }
}
