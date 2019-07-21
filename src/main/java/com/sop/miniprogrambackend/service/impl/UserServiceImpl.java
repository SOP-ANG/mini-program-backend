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
import com.sop.miniprogrambackend.service.ClockInService;
import com.sop.miniprogrambackend.service.domain.ClockInDomain;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private ClockInService clockInService;

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
        ValidationResult validationResult = this.validation.validate(userDomain);
        if(!validationResult.isPassed()) {
            throw new ResponseException(validationResult.getErrMsg(), EnumResponseError.DATA_VALIDATION_ERROR);
        }

        // 判断用户是否存在，存在更新数据后返回
        UserWxDO userWxDO = this.userWxDOMapper.selectByPrimaryKey(userDomain.getOpenid());
        if(userWxDO != null) {
            BeanUtils.copyProperties(userDomain, userWxDO);
            userWxDO.setSessionKey(userDomain.getSession_key());
            this.userWxDOMapper.updateByPrimaryKeySelective(userWxDO);
            Integer userId = userWxDO.getUserId();

            UserDO userDO = this.userDOMapper.selectByPrimaryKey(userWxDO.getUserId());
            BeanUtils.copyProperties(userDomain, userDO, new String[]{
                    "id", "nickName", "district", "school", "grade", "ignoreTs", "hasGiveThumbUp"});
            this.userDOMapper.updateByPrimaryKeySelective(userDO);
            UserDomain userDomainExisted = this.convertFromDataObject(userDO, userWxDO);

            // 获取打卡成功的数据
            List<Integer> userIds = new ArrayList<>();
            userIds.add(userDomainExisted.getId());
            Map<Integer, List<ClockInDomain>> idClockInMap = this.clockInService.getClockIn(userIds, true);
            if(idClockInMap != null && idClockInMap.containsKey(userDomainExisted.getId())) {
                userDomainExisted.setClockInDomainList(idClockInMap.get(userDomainExisted.getId()));
            }
            return userDomainExisted;
        }

        // 生成用户记录
        UserDO userDOCreate = this.convertUserFromDomain(userDomain);
        this.userDOMapper.insertSelective(userDOCreate);
        userDomain.setId(userDOCreate.getId());
        // 生成用户微信记录
        this.userWxDOMapper.insertSelective(this.convertUserWxFromDomain(userDomain));

        return userDomain;
    }

    /**
     * 获取所有用户
     * 只要 id、nickName、district、school、grade
     * @return
     */
    @Override
    public List<UserDomain> getUsers() {
        List<UserDO> userDOList = this.userDOMapper.selectAll();
        return userDOList.stream().map(this::convertUserFromDataObject).collect(Collectors.toList());
    }

    /**
     * 获取所有会员 id
     * 是否会员，主要看有没有填写年级
     * @return
     */
    @Override
    public List<Integer> getMemberIds() {
        List<UserDO> userDOList = this.userDOMapper.selectMemberId();
        return userDOList.stream().map(UserDO::getId).collect(Collectors.toList());
    }

    /**
     * 登记用户必要信息
     * @param userDomain
     */
    @Override
    @Transactional
    public void register(UserDomain userDomain) throws ResponseException {
        if(userDomain == null || StringUtils.isBlank(userDomain.getGrade())) {
            throw new ResponseException("用户注册登记信息不全", EnumResponseError.DATA_VALIDATION_ERROR);
        }
        UserDO userDO = this.userDOMapper.selectByPrimaryKey(userDomain.getId());
        if(userDO == null) {
            throw new ResponseException("用户不存在，需重新授权微信登录", EnumResponseError.DATA_VALIDATION_ERROR);
        }
        // 补充新用户数据
        BeanUtils.copyProperties(userDomain, userDO,
                new String[]{"id", "gender", "avatarUrl", "country", "province", "city"});
        // 更新用户入库
        this.userDOMapper.updateByPrimaryKeySelective(userDO);
        // 根据年级生成课文列表
        this.clockInService.generateClockInList(userDomain.getId(), userDomain.getGrade());
    }

    /**
     * 记录用户点赞行为
     * @param userDomain
     */
    @Override
    @Transactional
    public void thumbUp(UserDomain userDomain) {
        userDomain.setHasGiveThumbUp("yes");
        this.userDOMapper.updateHasGiveThumbUpByIdSelective(this.convertUserFromDomain(userDomain));
    }

    @Override
    public String getHasGiveThumbUp(UserDomain userDomain) {
        return this.userDOMapper.selectHasGiveThumbUpById(userDomain.getId()).getHasGiveThumbUp();
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

    public UserDomain convertUserFromDataObject(UserDO userDO) {
        if(userDO == null) {
            return null;
        }
        UserDomain userDomain = new UserDomain();
        BeanUtils.copyProperties(userDO, userDomain);
        return userDomain;
    }

    public UserDO convertUserFromDomain(UserDomain userDomain) {
        if(userDomain == null) {
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDomain, userDO);
        return userDO;
    }

    public UserWxDO convertUserWxFromDomain(UserDomain userDomain) {
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
