package com.sop.miniprogrambackend.service.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClockInDomain {
    private Integer id;
    private Integer user_id;
    private Integer course_id;
    private Integer times;
}
