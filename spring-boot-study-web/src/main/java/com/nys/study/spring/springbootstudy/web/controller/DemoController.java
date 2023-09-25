package com.nys.study.spring.springbootstudy.web.controller;

import com.nys.study.spring.springbootstudy.common.util.JsonUtil;
import com.nys.study.spring.springbootstudy.dto.SpringScheduledCronDto;
import com.nys.study.spring.springbootstudy.web.annotation.ResponseIntercept;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @ResponseIntercept
    @PostMapping(value = "/testException", produces = {"application/json;charset=UTF-8"})
    public int queryTaskList() {
        int i = 9/0;
        return i;
    }
}
