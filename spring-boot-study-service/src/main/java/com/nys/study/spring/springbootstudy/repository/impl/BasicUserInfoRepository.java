package com.nys.study.spring.springbootstudy.repository.impl;

import com.nys.study.spring.springbootstudy.convert.BasicUserInfoConvertor;
import com.nys.study.spring.springbootstudy.dao.mysql.entity.BasicUserInfoPO;
import com.nys.study.spring.springbootstudy.dao.mysql.mapper.BasicUserInfoPOExtMapper;
import com.nys.study.spring.springbootstudy.repository.IBasicUserInfoRepository;
import com.nys.study.spring.springbootstudy.dto.BasicUserInfoDto;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: TODO
 * @date 2023/9/10 11:47 AM
 */
@Repository
public class BasicUserInfoRepository implements IBasicUserInfoRepository {

    @Resource
    private BasicUserInfoPOExtMapper basicUserInfoPOExtMapper;

    @Override
    public List<BasicUserInfoDto> listBasicUserInfo() {
        List<BasicUserInfoPO> pos = basicUserInfoPOExtMapper.listBasicUserInfo();
        return BasicUserInfoConvertor.INSTANCE.po2Dtos(pos);
    }
}
