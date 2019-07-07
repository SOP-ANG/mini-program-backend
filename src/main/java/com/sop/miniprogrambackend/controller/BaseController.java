package com.sop.miniprogrambackend.controller;

import com.sop.miniprogrambackend.controller.view.ClockInView;
import com.sop.miniprogrambackend.controller.view.UserView;
import com.sop.miniprogrambackend.functional.response.EnumResponseError;
import com.sop.miniprogrambackend.functional.response.ResponseException;
import com.sop.miniprogrambackend.functional.response.ResponseResult;
import com.sop.miniprogrambackend.service.domain.ClockInDomain;
import com.sop.miniprogrambackend.service.domain.UserDomain;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public abstract class BaseController {

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

    protected UserView convertUserFromDomain(UserDomain userDomain) {
        if(userDomain == null) {
            return null;
        }
        UserView userView = new UserView();
        BeanUtils.copyProperties(userDomain, userView);
        userView.setClockInTimes(
                userDomain.getClockInDomainList() == null ? 0 : userDomain.getClockInDomainList().size());
        // 插入今天是否打卡
        userView.setHasClockIn(this.hasClockIn(userDomain.getClockInDomainList()));
        return userView;
    }

    protected ClockInView convertClockInFromDomain(ClockInDomain clockInDomain) {
        if(clockInDomain == null) {
            return null;
        }
        ClockInView clockInView = new ClockInView();
        BeanUtils.copyProperties(clockInDomain, clockInView);
        return clockInView;
    }

    private boolean hasClockIn(List<ClockInDomain> clockInDomainList) {
        if(clockInDomainList == null || clockInDomainList.size() == 0) {
            return false;
        }
        List<Long> clock_in_ts_list = new ArrayList<>();
        for(ClockInDomain clockInDomain: clockInDomainList) {
            if(StringUtils.isBlank(clockInDomain.getClockInTs())) {
                continue;
            }
            clock_in_ts_list.add(Long.valueOf(clockInDomain.getClockInTs()));
        }
        if(clock_in_ts_list.size() == 0) {
            return false;
        }
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
        return new DateTime().toString(format).equals(new DateTime(Collections.max(clock_in_ts_list)).toString(format));
    }
}
