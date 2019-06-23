package com.sop.miniprogrambackend.service;

import com.sop.miniprogrambackend.functional.response.ResponseException;
import com.sop.miniprogrambackend.service.domain.UserDomain;
import com.sop.miniprogrambackend.service.domain.WxApiDomain;

/**
 * 定义用户服务规范
 */
public interface UserService {
    public WxApiDomain validateWxLogin(String code) throws ResponseException;
    public UserDomain createUser(UserDomain userDomain) throws ResponseException;
}
