package com.sop.miniprogrambackend.service.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 集赞领域模型
 */
@Getter
@Setter
@ToString
public class CollectThumbsUpDomain {
    private Integer id;
    private Integer userId;
    private String workName;
    private String workProfile;
    private String imgPath;
    private Integer thumbsUpCount;
}
