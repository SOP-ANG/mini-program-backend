package com.sop.miniprogrambackend.service.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 用户领域模型
 */
@Getter
@Setter
@ToString
public class UserDomain {
    private Integer id;

    @NotBlank(message = "微信[临时登录凭证] code 不能为空")
    private String code;

    @NotBlank(message = "微信[登录凭证校验]结果 session_key 不能为空")
    private String session_key;

    @NotBlank(message = "微信[登录凭证校验]结果 openid 不能为空")
    private String openid;

    private String nickName;

    private Byte gender;

    private String avatarUrl;

    private String country;

    private String province;

    private String city;

    private String district;

    private String school;

    private String grade;

    // 用户打卡条目
    private List<ClockInDomain> clockInDomainList;
}
