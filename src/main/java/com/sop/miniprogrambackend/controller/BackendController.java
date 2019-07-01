package com.sop.miniprogrambackend.controller;

import com.sop.miniprogrambackend.functional.response.ResponseException;
import com.sop.miniprogrambackend.service.BackendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class BackendController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BackendController.class);

    @Autowired
    private BackendService backendService;

    /**
     * 首页
     * @param paramMap
     * @return
     */
    @RequestMapping("/")
    public String index(Map<String, Object> paramMap) {
        List<Integer> memberIds = this.backendService.getMemberIds();
        paramMap.put("member_count", memberIds.size());
        paramMap.put("finished_member_count", this.backendService.getFinishedMemberCount(memberIds));
        return "index";
    }

    /**
     * 用户列表
     * @param paramMap
     * @return
     */
    @RequestMapping("/userlist")
    public String getUserList(Map<String, Object> paramMap) {
        paramMap.put("userlist", this.backendService.getUsersInfo().stream().map(
                this::convertUserFromDomain).collect(Collectors.toList()));
        return "table";
    }

    /**
     * 用户课程详情
     * @param paramMap
     * @param userId
     * @param nickName
     * @return
     */
    @RequestMapping("/detail")
    public String getDetail(Map<String, Object> paramMap,
                            @RequestParam(name = "userId") Integer userId,
                            @RequestParam(name = "nickName") String nickName) throws ResponseException {
        paramMap.put("userId", userId);
        paramMap.put("nickName", nickName);

        paramMap.put("clockInlist", this.backendService.getFinishedClockInList(userId).stream().map(
                this::convertClockInFromDomain).collect(Collectors.toList()));
        return "detail";
    }
}
