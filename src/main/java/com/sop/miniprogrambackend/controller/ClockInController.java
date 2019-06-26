package com.sop.miniprogrambackend.controller;

import com.sop.miniprogrambackend.functional.response.EnumResponseError;
import com.sop.miniprogrambackend.functional.response.ResponseException;
import com.sop.miniprogrambackend.functional.response.ResponseResult;
import com.sop.miniprogrambackend.service.ClockInService;
import com.sop.miniprogrambackend.service.domain.ClockInDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clockIn")
public class ClockInController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ClockInController.class);

    @Autowired
    private ClockInService clockInService;

    /**
     * 获取“年级”对应的课文列表接口
     * @param clockInDomain
     * @return
     */
    @PostMapping("/getCourseList")
    public ResponseResult getCourseList(@RequestBody ClockInDomain clockInDomain) throws ResponseException {
        if(clockInDomain.getUserId() == null || clockInDomain.getUserId() == 0) {
            throw new ResponseException("会员 id 必传", EnumResponseError.DATA_VALIDATION_ERROR);
        }
        List<ClockInDomain> clockInDomainList = this.clockInService.getClockInWithCourseByUserId(clockInDomain.getUserId());
        return ResponseResult.generate(clockInDomainList);
    }

    /**
     * 打卡成功记录打卡成功接口（分享朋友圈成功后）
     * @param clockInDomain
     * @return
     */
    @PostMapping("/clockInDone")
    public ResponseResult clockInDone(@RequestBody ClockInDomain clockInDomain) {
        this.clockInService.clockInDone(clockInDomain);
        return ResponseResult.generate(null);
    }
}
