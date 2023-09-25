package com.nys.study.spring.springbootstudy.web.vo.base;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 返回结果工具
 * @date 2023/9/22 5:08 PM
 */
public class ResponseResultUtil {

    public static ResponseResultVO renderSuccessResult(Object result) {
        ResponseResultVO responseResultVo = new ResponseResultVO();
        responseResultVo.setData(result);
        responseResultVo.setCode(ResultEnum.SUCCESS.getCode());
        responseResultVo.setMessage(ResultEnum.SUCCESS.getMessage());
        return responseResultVo;
    }

    public static ResponseResultVO renderFailResult(ResultEnum resultEnum, String errorMsg) {
        ResponseResultVO responseResultVo = new ResponseResultVO();
        responseResultVo.setCode(resultEnum.getCode());
        responseResultVo.setMessage(errorMsg);
        return responseResultVo;
    }

    public static ResponseResultVO renderFailResultByBusinessException(String errorMsg) {
        ResponseResultVO responseResultVo = new ResponseResultVO();
        responseResultVo.setCode(ResultEnum.COMMON_FAILED.getCode());
        responseResultVo.setMessage(errorMsg);
        return responseResultVo;
    }

}
