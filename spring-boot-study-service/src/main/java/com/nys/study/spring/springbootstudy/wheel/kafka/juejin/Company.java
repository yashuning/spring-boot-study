package com.nys.study.spring.springbootstudy.wheel.kafka.juejin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: 示例bean -- Company 对象
 * @date 2023/12/8 16:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    private String name;
    private String address;
}
