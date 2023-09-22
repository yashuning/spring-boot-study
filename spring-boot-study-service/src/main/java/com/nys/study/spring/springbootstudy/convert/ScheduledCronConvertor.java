package com.nys.study.spring.springbootstudy.convert;

import com.nys.study.spring.springbootstudy.dao.mysql.entity.SpringScheduledCronPO;
import com.nys.study.spring.springbootstudy.dto.SpringScheduledCronDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 转换
 * @date 2023/9/21 5:55 PM
 */
@Mapper
public interface ScheduledCronConvertor {
    ScheduledCronConvertor INSTANCE = Mappers.getMapper(ScheduledCronConvertor.class);

    SpringScheduledCronDto po2Dto(SpringScheduledCronPO po);

    List<SpringScheduledCronDto> pos2Dtos(List<SpringScheduledCronPO> pos);
}
