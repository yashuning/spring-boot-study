package com.nys.study.spring.springbootstudy.convert;

import com.nys.study.spring.springbootstudy.dao.mysql.entity.BasicUserInfoPO;
import com.nys.study.spring.springbootstudy.dto.BasicUserInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: BasicUserInfo converts
 * @date 2023/9/10 11:24 AM
 */
@Mapper
public interface BasicUserInfoConvertor {
    BasicUserInfoConvertor INSTANCE = Mappers.getMapper(BasicUserInfoConvertor.class);

    BasicUserInfoDto po2Dto(BasicUserInfoPO po);

    List<BasicUserInfoDto> po2Dtos(List<BasicUserInfoPO> pos);
}
