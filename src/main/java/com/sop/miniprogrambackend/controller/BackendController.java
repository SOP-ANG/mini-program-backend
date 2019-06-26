package com.sop.miniprogrambackend.controller;

import com.sop.miniprogrambackend.controller.view.UserView;
import com.sop.miniprogrambackend.service.BackendService;
import com.sop.miniprogrambackend.service.domain.UserDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

    @RequestMapping("/")
    public String index(Map<String, Object> paramMap) {
        List<Integer> memberIds = this.backendService.getMemberIds();
        paramMap.put("member_count", memberIds.size());
        paramMap.put("finished_member_count", this.backendService.getFinishedMemberCount(memberIds));
        return "index";
    }

    @RequestMapping("/userlist")
    public String getUserList(Map<String, Object> paramMap) {
        paramMap.put("userlist", this.backendService.getUsersInfo().stream().map(this::convertFromDomain).collect(Collectors.toList()));
        return "table";
    }

    @RequestMapping("/detail")
    public String getDetail(Map<String, Object> paramMap,
                            @RequestParam(name = "userId") Integer userId,
                            @RequestParam(name = "nickName") String nickName) {
        paramMap.put("userId", userId);
        paramMap.put("nickName", nickName);
        return "detail";
    }

    public UserView convertFromDomain(UserDomain userDomain) {
        if(userDomain == null) {
            return null;
        }
        UserView userView = new UserView();
        BeanUtils.copyProperties(userDomain, userView);
        userView.setClockInTimes(userDomain.getClockInDomainList() == null ? 0 : userDomain.getClockInDomainList().size());
        return userView;
    }
}
