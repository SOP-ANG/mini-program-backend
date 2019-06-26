package com.sop.miniprogrambackend.controller;

import com.sop.miniprogrambackend.functional.response.EnumResponseError;
import com.sop.miniprogrambackend.functional.response.ResponseException;
import com.sop.miniprogrambackend.functional.response.ResponseResult;
import com.sop.miniprogrambackend.service.ClockInService;
import com.sop.miniprogrambackend.service.domain.ClockInDomain;
import com.sop.miniprogrambackend.service.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clockIn")
public class ClockInController extends BaseController {

    @Autowired
    private ClockInService clockInService;

    /**
     * 获取会员的所有课文
     * @param userDomain
     * @return
     */
    @PostMapping("/getCourseList")
    public ResponseResult getCourseList(@RequestBody UserDomain userDomain) throws ResponseException {
        if(userDomain.getId() == null || userDomain.getId() == 0) {
            throw new ResponseException("会员 id 必传", EnumResponseError.DATA_VALIDATION_ERROR);
        }
        List<ClockInDomain> clockInDomainList = this.clockInService.getClockInWithCourseByUserId(userDomain.getId());
        return ResponseResult.generate(clockInDomainList);
    }
}
