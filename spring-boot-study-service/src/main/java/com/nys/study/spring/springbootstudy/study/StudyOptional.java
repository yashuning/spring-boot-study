package com.nys.study.spring.springbootstudy.study;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: optional语法学习
 * @date 2024/3/14 18:02
 */
public class StudyOptional {


    public static void main(String[] args) {
        StudyOptional optional = new StudyOptional();
        optional.optional1("");
        optional.optional1("111");
        optional.optional1("0");
        optional.optional1(null);
    }

    public Long optional1(Object idObject){
        Long idLong = Optional.ofNullable(idObject)
                .map(Object::toString)
                .filter(StringUtils::isNotBlank)
                .map(Long::parseLong)
                .filter(id -> id > 0)
                .orElse(-1L);
        System.out.println(String.format("optional1========>idObject=%s, result=%s", idObject, idLong));
        return idLong;
    }
}
