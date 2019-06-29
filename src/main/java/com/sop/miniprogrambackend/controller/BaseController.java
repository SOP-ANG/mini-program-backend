package com.sop.miniprogrambackend.controller;

import com.sop.miniprogrambackend.controller.view.ClockInView;
import com.sop.miniprogrambackend.functional.response.EnumResponseError;
import com.sop.miniprogrambackend.functional.response.ResponseException;
import com.sop.miniprogrambackend.functional.response.ResponseResult;
import com.sop.miniprogrambackend.service.domain.ClockInDomain;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    /**
     * 定义一个 exception handler 解决未被 controller 层吸收的 exception
     * @param httpServletRequest
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Object handlerException(HttpServletRequest httpServletRequest, Exception ex) {
        Map<String, Object> responseData = new HashMap<>();
        if(ex instanceof ResponseException) {
            ResponseException responseException = (ResponseException) ex;
            responseData.put("errCode", responseException.getErrCode());
            responseData.put("errMsg", responseException.getErrMsg());
        } else {
            responseData.put("errCode", EnumResponseError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg", EnumResponseError.UNKNOWN_ERROR.getErrMsg());
        }
        return ResponseResult.generate(responseData, "failure");
    }

    public ClockInView convertClockInFromDomain(ClockInDomain clockInDomain) {
        if(clockInDomain == null) {
            return null;
        }
        ClockInView clockInView = new ClockInView();
        BeanUtils.copyProperties(clockInDomain, clockInView);
        return clockInView;
    }
}
