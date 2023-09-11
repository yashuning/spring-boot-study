package com.nys.study.spring.springbootstudy.dao.mysql.mapper;

import com.nys.study.spring.springbootstudy.dao.mysql.entity.BasicUserInfoPO;
import com.nys.study.spring.springbootstudy.dao.mysql.entity.BasicUserInfoPOExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

/**
* Created by Mybatis Generator on 2023/09/08
*/
public interface BasicUserInfoPOMapper {
    int insert(BasicUserInfoPO record);

    int insertSelective(BasicUserInfoPO record);

    List<BasicUserInfoPO> selectByExampleWithRowbounds(BasicUserInfoPOExample example, RowBounds rowBounds);

    List<BasicUserInfoPO> selectByExample(BasicUserInfoPOExample example);

    int updateByExampleSelective(@Param("record") BasicUserInfoPO record, @Param("example") BasicUserInfoPOExample example);

    int updateByExample(@Param("record") BasicUserInfoPO record, @Param("example") BasicUserInfoPOExample example);

    int updateByPrimaryKeySelective(BasicUserInfoPO record);

    int updateByPrimaryKey(BasicUserInfoPO record);
}