package com.sop.miniprogrambackend.service.impl;

import com.sop.miniprogrambackend.functional.conf.MiniProgramBackendConf;
import com.sop.miniprogrambackend.functional.response.EnumResponseError;
import com.sop.miniprogrambackend.functional.utils.RestTemplateClient;
import com.sop.miniprogrambackend.functional.response.ResponseException;
import com.sop.miniprogrambackend.functional.validator.ValidationImpl;
import com.sop.miniprogrambackend.functional.validator.ValidationResult;
import com.sop.miniprogrambackend.model.UserDOMapper;
import com.sop.miniprogrambackend.model.UserWxDOMapper;
import com.sop.miniprogrambackend.model.data.UserDO;
import com.sop.miniprogrambackend.model.data.UserWxDO;
import com.sop.miniprogrambackend.service.domain.UserDomain;
import com.sop.miniprogrambackend.service.domain.WxApiDomain;
import com.sop.miniprogrambackend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private MiniProgramBackendConf conf;

    @Autowired
    private ValidationImpl validation;

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserWxDOMapper userWxDOMapper;

    /**
     * 登录凭证校验
     * 请求地址：https://api.weixin.qq.com/sns/jscode2session?
     *      appid=APPID&
     *      secret=SECRET&
     *      js_code=JSCODE&
     *      grant_type=authorization_code
     * @param code
     * @return
     */
    @Override
    public WxApiDomain validateWxLogin(String code) throws ResponseException {
        WxApiDomain wxApiDomain = RestTemplateClient.getInstance().getForObject(
                this.conf.getJscode2session_uri(), WxApiDomain.class,
                this.conf.getAppid(), this.conf.getSecret(), code);

        if(StringUtils.isBlank(wxApiDomain.getOpenid()) || StringUtils.isBlank(wxApiDomain.getSession_key())) {
            throw new ResponseException("[登录凭证校验]失败", EnumResponseError.REQUEST_ERROR);
        }
        return wxApiDomain;
    }

    /**
     * 创建用户
     * @param userDomain
     * @return
     * @throws ResponseException
     */
    @Override
    @Transactional
    public UserDomain createUser(UserDomain userDomain) throws ResponseException {
        // 必要数据校验
        if(userDomain == null) {
            throw new ResponseException(EnumResponseError.DATA_VALIDATION_ERROR);
        }
        ValidationResult validationResult = this.validation.validate(userDomain);
        if(!validationResult.isPassed()) {
            throw new ResponseException(validationResult.getErrMsg(), EnumResponseError.DATA_VALIDATION_ERROR);
        }

        // 判断用户是否存在，存在即返回
        UserWxDO userWxDO = this.userWxDOMapper.selectByPrimaryKey(userDomain.getOpenid());
        if(userWxDO != null) {
            UserDO userDO = this.userDOMapper.selectByPrimaryKey(userWxDO.getUserId());
            return this.convertFromDataObject(userDO, userWxDO);
        }

        // 生成用户记录
        UserDO userDOCreate = this.convertFromDomain(userDomain);
        this.userDOMapper.insertSelective(userDOCreate);
        userDomain.setId(userDOCreate.getId());
        // 生成用户微信记录
        this.userWxDOMapper.insertSelective(this.convertWxFromDomain(userDomain));

        return userDomain;
    }

    public UserDomain convertFromDataObject(UserDO userDO, UserWxDO userWxDO) {
        if(userDO == null || userWxDO == null) {
            return null;
        }
        UserDomain userDomain = new UserDomain();
        BeanUtils.copyProperties(userDO, userDomain);
        BeanUtils.copyProperties(userWxDO, userDomain);
        userDomain.setSession_key(userWxDO.getSessionKey());
        return userDomain;
    }

    public UserDO convertFromDomain(UserDomain userDomain) {
        if(userDomain == null) {
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDomain, userDO);
        return userDO;
    }

    public UserWxDO convertWxFromDomain(UserDomain userDomain) {
        if(userDomain == null) {
            return null;
        }
        UserWxDO userWxDO = new UserWxDO();
        BeanUtils.copyProperties(userDomain, userWxDO);
        userWxDO.setSessionKey(userDomain.getSession_key());
        userWxDO.setUserId(userDomain.getId());
        return userWxDO;
    }

}
