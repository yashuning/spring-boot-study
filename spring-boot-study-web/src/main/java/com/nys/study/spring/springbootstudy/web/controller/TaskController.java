package com.nys.study.spring.springbootstudy.web.controller;

import com.nys.study.spring.springbootstudy.common.util.JsonUtil;
import com.nys.study.spring.springbootstudy.dto.SpringScheduledCronDto;
import com.nys.study.spring.springbootstudy.service.api.ISpringScheduledCronService;
import com.nys.study.spring.springbootstudy.web.annotation.ResponseIntercept;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 定时任务controller 管理定时任务
 * @date 2023/9/22 3:34 PM
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Resource
    private ISpringScheduledCronService springScheduledCronService;

    @ResponseIntercept
    @PostMapping(value = "/queryTaskList", produces = {"application/json;charset=UTF-8"})
    public String queryTaskList() {
        List<SpringScheduledCronDto> allTask = springScheduledCronService.findAllTask();
        return JsonUtil.writeToString(allTask);
    }

    @ResponseIntercept
    @PostMapping(value = "/editTaskCron", produces = {"application/json;charset=UTF-8"})
    public String editTaskCron() {
        List<SpringScheduledCronDto> allTask = springScheduledCronService.findAllTask();
        return JsonUtil.writeToString(allTask);
    }
}
