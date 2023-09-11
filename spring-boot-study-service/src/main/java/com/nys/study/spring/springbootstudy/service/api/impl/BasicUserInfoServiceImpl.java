package com.nys.study.spring.springbootstudy.service.api.impl;

import com.nys.study.spring.springbootstudy.repository.IBasicUserInfoRepository;
import com.nys.study.spring.springbootstudy.service.api.IBasicUserInfoService;
import com.nys.study.spring.springbootstudy.service.dto.BasicUserInfoDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: TODO
 * @date 2023/9/10 12:12 PM
 */
@Service
public class BasicUserInfoServiceImpl implements IBasicUserInfoService {
    @Resource
    private IBasicUserInfoRepository basicUserInfoRepository;

    @Override
    public List<BasicUserInfoDto> listBasicUserInfo() {
        return basicUserInfoRepository.listBasicUserInfo();
    }
}
