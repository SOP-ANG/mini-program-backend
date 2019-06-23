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

    private UserView convertFromDomain(UserDomain userDomain) {
        if(userDomain == null) {
            return null;
        }
        UserView userView = new UserView();
        BeanUtils.copyProperties(userDomain, userView);
        return userView;
    }

//    public ResponseResult validateWxLogin(@RequestParam(name = "code") String code,
//                                          @RequestParam(name = "nickName") String nickName,
//                                          @RequestParam(name = "gender") Byte gender,
//                                          @RequestParam(name = "avatarUrl") String avatarUrl,
//                                          @RequestParam(name = "country") String country,
//                                          @RequestParam(name = "province") String province,
//                                          @RequestParam(name = "city") String city) throws ResponseException

}
