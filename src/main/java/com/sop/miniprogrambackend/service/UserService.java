package com.sop.miniprogrambackend.service;

import com.sop.miniprogrambackend.functional.response.ResponseException;
import com.sop.miniprogrambackend.service.domain.UserDomain;
import com.sop.miniprogrambackend.service.domain.WxApiDomain;

import java.util.List;

/**
 * 定义用户服务规范
 */
public interface UserService {
    /**
     * 登录凭证校验
     * @param code
     * @return
     * @throws ResponseException
     */
    public WxApiDomain validateWxLogin(String code) throws ResponseException;

    /**
     * 创建用户
     * @param userDomain
     * @return
     * @throws ResponseException
     */
    public UserDomain createUser(UserDomain userDomain) throws ResponseException;

    /**
     * 获取所有用户
     * 只要 id、nickName、district、school、grade
     * @return
     */
    public List<UserDomain> getUsers();

    /**
     * 获取所有会员 id
     * 是否会员，主要看有没有填写年级
     * @return
     */
    public List<Integer> getMemberIds();
}
