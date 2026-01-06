package com.nys.study.spring.springbootstudy.wheel.kafka.juejin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DomainWrapper<T> {

    private T domain;

    public static <T> DomainWrapper<T> of(T obj) {
        return new DomainWrapper<>(obj);
    }

}
