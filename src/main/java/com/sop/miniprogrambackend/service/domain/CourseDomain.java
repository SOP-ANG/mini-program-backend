package com.sop.miniprogrambackend.service.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 课程领域模型
 */
@Getter
@Setter
@ToString
public class CourseDomain {
    private Integer id;
    private String title;
    private String content;
    private String grade;

}