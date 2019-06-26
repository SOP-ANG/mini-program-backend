package com.sop.miniprogrambackend.controller;

import com.sop.miniprogrambackend.functional.response.EnumResponseError;
import com.sop.miniprogrambackend.functional.response.ResponseException;
import com.sop.miniprogrambackend.functional.response.ResponseResult;
import com.sop.miniprogrambackend.service.domain.UserDomain;
import com.sop.miniprogrambackend.controller.view.UserView;
import com.sop.miniprogrambackend.service.UserService;
import com.sop.miniprogrambackend.service.domain.WxApiDomain;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 校验用户登录凭证
     * @param userDomain
     * @return
     */
    @PostMapping("/validateWxLogin")
    public ResponseResult validateWxLogin(@RequestBody UserDomain userDomain) throws ResponseException {
        if(userDomain == null || StringUtils.isBlank(userDomain.getCode())) {
            throw new ResponseException("[临时登录凭证] code 为空", EnumResponseError.DATA_VALIDATION_ERROR);
        }
        WxApiDomain wxApiDomain = this.userService.validateWxLogin(userDomain.getCode());
        BeanUtils.copyProperties(wxApiDomain, userDomain);

        UserDomain userDomainRes = this.userService.createUser(userDomain);
        return ResponseResult.generate(this.convertFromDomain(userDomainRes));
    }

    /**
     * 用户登记，登记成功后生成"年级"对应的课文
     * @param userDomain
     * @return
     */
    @PostMapping("/register")
    public ResponseResult register(@RequestBody UserDomain userDomain) throws ResponseException {
        if(userDomain.getId() == null || userDomain.getId() == 0) {
            throw new ResponseException("传入用户 id 错误", EnumResponseError.DATA_VALIDATION_ERROR);
        }
        if(StringUtils.isBlank(userDomain.getGrade())) {
            throw new ResponseException("用户注册登记时必须填写所在\"年级\"", EnumResponseError.DATA_VALIDATION_ERROR);
        }
        this.userService.register(userDomain);
        return ResponseResult.generate(null);
    }

    private UserView convertFromDomain(UserDomain userDomain) {
        if(userDomain == null) {
            return null;
        }
        UserView userView = new UserView();
        BeanUtils.copyProperties(userDomain, userView);
        return userView;
    }

}
