package com.sop.miniprogrambackend.controller;

import com.sop.miniprogrambackend.functional.response.ResponseException;
import com.sop.miniprogrambackend.model.AdminDOMapper;
import com.sop.miniprogrambackend.model.data.AdminDO;
import com.sop.miniprogrambackend.service.BackendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class BackendController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BackendController.class);

    @Autowired
    private BackendService backendService;

    //这个通过 Spring bean 包装的 httpservletrequest 其本质是一个 proxy，它内部拥有 thread local 的 map，让用户在每一个线程中处理它自己对应的 request，并且有 thread local 清除的机制，因此可以支持多个用户并发的访问
    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 首页
     * @param paramMap
     * @return
     */
    @RequestMapping("/dashboard")
    public String dashboard(Map<String, Object> paramMap) {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if(isLogin == null || !isLogin.booleanValue()) {
            return "loginfailed";
        }

        List<Integer> memberIds = this.backendService.getMemberIds();
        paramMap.put("member_count", memberIds.size());
        paramMap.put("finished_member_count", this.backendService.getFinishedMemberCount(memberIds));
        return "dashboard";
    }

    /**
     * 用户列表
     * @param paramMap
     * @return
     */
    @RequestMapping("/userlist")
    public String getUserList(Map<String, Object> paramMap) {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if(isLogin == null || !isLogin.booleanValue()) {
            return "loginfailed";
        }

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
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if(isLogin == null || !isLogin.booleanValue()) {
            return "loginfailed";
        }

        paramMap.put("userId", userId);
        paramMap.put("nickName", nickName);

        paramMap.put("clockInlist", this.backendService.getFinishedClockInList(userId).stream().map(
                this::convertClockInFromDomain).collect(Collectors.toList()));
        return "detail";
    }

    ////////////////////////////////////// 临时功能 ///////////////////////////////////////////

    /**
     * 登录页（临时工能）
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "login";
    }

    @Autowired
    private AdminDOMapper adom;

    /**
     * 用户登录（临时工能）
     * @param paramMap
     * @param username
     * @param password
     * @return
     * @throws ResponseException
     */
    @RequestMapping("/login")
    public String login(Map<String, Object> paramMap,
                                @RequestParam(name = "username") String username,
                                @RequestParam(name = "password") String password) throws ResponseException {

        AdminDO adminDO = this.adom.selectByUsernamePassword(username, password);
        if(adminDO == null) {
            return "loginfailed";
        }
        httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        return "loginsuccess";
    }
}
