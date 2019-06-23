package com.sop.miniprogrambackend.service.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 微信[登录凭证校验]接口模型
 */
@Getter
@Setter
@ToString
public class WxApiDomain {
    private String openid;
    private String session_key;
    private String unionid;
    private Number errcode;
    private String errmsg;
}
