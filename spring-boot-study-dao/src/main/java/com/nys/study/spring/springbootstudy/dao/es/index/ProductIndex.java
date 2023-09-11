package com.nys.study.spring.springbootstudy.dao.es.index;

import lombok.Data;

import java.util.List;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: product索引
 * @date 2022/12/20 12:05 下午
 */
@Data
public class ProductIndex {

    private String name;

    private Long price;

    private List<String> tags;
    
    private String desc;
}
