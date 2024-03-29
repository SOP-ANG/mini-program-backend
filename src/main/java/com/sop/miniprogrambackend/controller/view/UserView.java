package com.sop.miniprogrambackend.controller.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户视图模型
 */
@Getter
@Setter
@ToString
public class UserView {
    private Integer id;
    private String nickName;
    private String district;
    private String school;
    private String grade;
    private Integer clockInTimes;
    private boolean hasClockIn;
    private String ignoreTs;    // 忽略时间限制能查看集赞活动
    private String hasGiveThumbUp;
}
