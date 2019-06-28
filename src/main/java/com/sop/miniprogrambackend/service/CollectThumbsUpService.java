package com.sop.miniprogrambackend.service;

import com.sop.miniprogrambackend.functional.response.ResponseException;
import com.sop.miniprogrambackend.service.domain.CollectThumbsUpDomain;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 定义集赞服务规范
 */
public interface CollectThumbsUpService {
    /**
     * 上传图片
     * @param file
     * @param userId
     * @return
     */
    public String uploadImg(MultipartFile file, Integer userId) throws ResponseException;

    /**
     * 创建会员的集赞活动
     * @param userId
     * @param workName
     * @param workProfile
     * @param imgPath
     * @throws ResponseException
     */
    public void create(Integer userId, String workName, String workProfile, String imgPath) throws ResponseException;

    /**
     * 获取集赞数据
     * @return
     */
    public List<CollectThumbsUpDomain> getAll();
}
