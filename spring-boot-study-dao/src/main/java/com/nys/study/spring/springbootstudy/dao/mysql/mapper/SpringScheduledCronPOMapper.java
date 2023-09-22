package com.nys.study.spring.springbootstudy.dao.mysql.mapper;

import com.nys.study.spring.springbootstudy.dao.mysql.entity.SpringScheduledCronPO;
import com.nys.study.spring.springbootstudy.dao.mysql.entity.SpringScheduledCronPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

/**
* Created by Mybatis Generator on 2023/09/21
*/
public interface SpringScheduledCronPOMapper {
    int insert(SpringScheduledCronPO record);

    int insertSelective(SpringScheduledCronPO record);

    List<SpringScheduledCronPO> selectByExampleWithRowbounds(SpringScheduledCronPOExample example, RowBounds rowBounds);

    List<SpringScheduledCronPO> selectByExample(SpringScheduledCronPOExample example);

    int updateByExampleSelective(@Param("record") SpringScheduledCronPO record, @Param("example") SpringScheduledCronPOExample example);

    int updateByExample(@Param("record") SpringScheduledCronPO record, @Param("example") SpringScheduledCronPOExample example);

    int updateByPrimaryKeySelective(SpringScheduledCronPO record);

    int updateByPrimaryKey(SpringScheduledCronPO record);
}