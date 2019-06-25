package com.sop.miniprogrambackend.service.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 打卡领域模型
 */
@Getter
@Setter
@ToString
public class ClockInDomain {
    private Integer id;
    private Integer userId;
    private Integer courseId;
    private boolean done;
}
