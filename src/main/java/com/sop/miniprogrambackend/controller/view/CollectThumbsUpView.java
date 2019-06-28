package com.sop.miniprogrambackend.controller.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 集赞视图模型
 */
@Getter
@Setter
@ToString
public class CollectThumbsUpView {
    private Integer id;
    private Integer userId;
    private String workName;
    private String workProfile;
    private String imgPath;
    private Integer thumbsUpCount;
}
