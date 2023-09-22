package com.nys.study.spring.springbootstudy.repository.impl;

import com.nys.study.spring.springbootstudy.convert.ScheduledCronConvertor;
import com.nys.study.spring.springbootstudy.dao.mysql.entity.SpringScheduledCronPO;
import com.nys.study.spring.springbootstudy.dao.mysql.mapper.SpringScheduledCronPOExtMapper;
import com.nys.study.spring.springbootstudy.dto.SpringScheduledCronDto;
import com.nys.study.spring.springbootstudy.repository.ISpringScheduledCronRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 定时任务仓库
 * @date 2023/9/21 5:32 PM
 */
@Repository
public class SpringScheduledCronRepository implements ISpringScheduledCronRepository {

    @Resource
    private SpringScheduledCronPOExtMapper springScheduledCronPOExtMapper;

    /**
     * 获取所有定时任务
     *
     * @return
     */
    @Override
    public List<SpringScheduledCronDto> listAll() {
        List<SpringScheduledCronPO> pos = springScheduledCronPOExtMapper.listAll();
        if (CollectionUtils.isEmpty(pos)) {
            return Collections.emptyList();
        }
        return ScheduledCronConvertor.INSTANCE.pos2Dtos(pos);
    }

    @Override
    public SpringScheduledCronDto selectById(Long cronId) {
        SpringScheduledCronPO po = springScheduledCronPOExtMapper.selectById(cronId);
        if (Objects.isNull(po)) {
            return null;
        }
        return ScheduledCronConvertor.INSTANCE.po2Dto(po);
    }

    @Override
    public SpringScheduledCronDto selectByCronKey(String cronKey) {
        SpringScheduledCronPO po = springScheduledCronPOExtMapper.selectByCronKey(cronKey);
        if (Objects.isNull(po)) {
            return null;
        }
        return ScheduledCronConvertor.INSTANCE.po2Dto(po);
    }


}
