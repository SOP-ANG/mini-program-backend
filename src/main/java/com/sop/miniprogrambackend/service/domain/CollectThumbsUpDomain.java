package com.sop.miniprogrambackend.service.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 集赞领域模型
 */
@Getter
@Setter
@ToString
public class CollectThumbsUpDomain {
    private Integer id;
    @NotNull(message = "用户 id 必传")
    private Integer userId;
    @NotBlank(message = "作品名不能为空")
    private String workName;
    @NotBlank(message = "作品简介不能为空")
    private String workProfile;
    @NotBlank(message = "图片没上传")
    private String imgPath;
    private Integer thumbsUpCount;
}
