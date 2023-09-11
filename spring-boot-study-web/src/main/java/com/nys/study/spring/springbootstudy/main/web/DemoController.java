package com.nys.study.spring.springbootstudy.main.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description:
 * @date 2022/12/7 7:00 下午
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/echo")
    public String echo(){
        return "echo!";
    }
}
