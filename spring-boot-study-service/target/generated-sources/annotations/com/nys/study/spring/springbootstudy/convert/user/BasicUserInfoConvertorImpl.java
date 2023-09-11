package com.nys.study.spring.springbootstudy.convert.user;

import com.nys.study.spring.springbootstudy.dao.mysql.entity.BasicUserInfoPO;
import com.nys.study.spring.springbootstudy.service.dto.BasicUserInfoDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-11T22:42:10+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
public class BasicUserInfoConvertorImpl implements BasicUserInfoConvertor {

    @Override
    public BasicUserInfoDto po2Dto(BasicUserInfoPO po) {
        if ( po == null ) {
            return null;
        }

        BasicUserInfoDto basicUserInfoDto = new BasicUserInfoDto();

        basicUserInfoDto.setId( po.getId() );
        basicUserInfoDto.setApp( po.getApp() );
        basicUserInfoDto.setUserName( po.getUserName() );
        basicUserInfoDto.setPassword( po.getPassword() );
        basicUserInfoDto.setRemark( po.getRemark() );

        return basicUserInfoDto;
    }

    @Override
    public List<BasicUserInfoDto> po2Dtos(List<BasicUserInfoPO> pos) {
        if ( pos == null ) {
            return null;
        }

        List<BasicUserInfoDto> list = new ArrayList<BasicUserInfoDto>( pos.size() );
        for ( BasicUserInfoPO basicUserInfoPO : pos ) {
            list.add( po2Dto( basicUserInfoPO ) );
        }

        return list;
    }
}
