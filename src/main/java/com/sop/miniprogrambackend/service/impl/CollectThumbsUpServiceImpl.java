package com.sop.miniprogrambackend.service.impl;

import com.sop.miniprogrambackend.functional.conf.MiniProgramBackendConf;
import com.sop.miniprogrambackend.functional.response.EnumResponseError;
import com.sop.miniprogrambackend.functional.response.ResponseException;
import com.sop.miniprogrambackend.functional.validator.ValidationImpl;
import com.sop.miniprogrambackend.functional.validator.ValidationResult;
import com.sop.miniprogrambackend.model.CollectThumbsUpDOMapper;
import com.sop.miniprogrambackend.model.data.CollectThumbsUpDO;
import com.sop.miniprogrambackend.service.CollectThumbsUpService;
import com.sop.miniprogrambackend.service.domain.CollectThumbsUpDomain;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectThumbsUpServiceImpl implements CollectThumbsUpService {

    @Autowired
    private MiniProgramBackendConf conf;

    @Autowired
    private ValidationImpl validation;

    @Autowired
    private CollectThumbsUpDOMapper collectThumbsUpDOMapper;

    /**
     * 上传图片
     * @param file
     * @param userId
     * @return
     */
    @Override
    public String uploadImg(MultipartFile file, Integer userId) throws ResponseException {
        String fileName = file.getOriginalFilename();
        String targetFileName = MessageFormat.format(
                this.conf.getImg_file_name(),
                new Object[]{"Img", userId, fileName.substring(fileName.lastIndexOf(".") + 1)});
        File targetFile = new File(this.conf.getImg_path(), targetFileName);
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            throw new ResponseException("图片文件存储失败", EnumResponseError.DATA_CONVERT_ERROR);
        }
        return this.conf.getRelative_img_path() + targetFileName;
    }

    /**
     * 创建会员的集赞活动
     * @param userId
     * @param workName
     * @param workProfile
     * @param imgPath
     * @throws ResponseException
     */
    @Override
    @Transactional
    public void create(Integer userId, String workName, String workProfile, String imgPath) throws ResponseException {
        // 数据校验
        CollectThumbsUpDomain collectThumbsUpDomain = new CollectThumbsUpDomain();
        collectThumbsUpDomain.setUserId(userId);
        collectThumbsUpDomain.setWorkName(workName);
        collectThumbsUpDomain.setWorkProfile(workProfile);
        collectThumbsUpDomain.setImgPath(imgPath);
        ValidationResult validationResult = this.validation.validate(collectThumbsUpDomain);
        if(!validationResult.isPassed()) {
            throw new ResponseException(validationResult.getErrMsg(), EnumResponseError.DATA_VALIDATION_ERROR);
        }

        // 判断用户是否已经参加了集赞活动
        CollectThumbsUpDO collectThumbsUpDO = this.collectThumbsUpDOMapper.selectByUserId(
                collectThumbsUpDomain.getUserId());
        if(collectThumbsUpDO != null && collectThumbsUpDO.getId() != 0) {
            throw new ResponseException("会员已经参加过集赞活动", EnumResponseError.DATA_VALIDATION_ERROR);
        }

        this.collectThumbsUpDOMapper.insertSelective(this.convertFromDomain(collectThumbsUpDomain));
    }

    /**
     * 获取集赞数据
     * @return
     */
    @Override
    public List<CollectThumbsUpDomain> getAll() {
        List<CollectThumbsUpDO> collectThumbsUpDOList = this.collectThumbsUpDOMapper.selectOrderByThumbsUpCount();
        return collectThumbsUpDOList.stream().map(this::convertFromDataObject).collect(Collectors.toList());
    }

    public CollectThumbsUpDO convertFromDomain(CollectThumbsUpDomain collectThumbsUpDomain) {
        if(collectThumbsUpDomain == null) {
            return null;
        }
        CollectThumbsUpDO collectThumbsUpDO = new CollectThumbsUpDO();
        BeanUtils.copyProperties(collectThumbsUpDomain, collectThumbsUpDO);
        return collectThumbsUpDO;
    }

    public CollectThumbsUpDomain convertFromDataObject(CollectThumbsUpDO collectThumbsUpDO) {
        if(collectThumbsUpDO == null) {
            return null;
        }
        CollectThumbsUpDomain collectThumbsUpDomain = new CollectThumbsUpDomain();
        BeanUtils.copyProperties(collectThumbsUpDO, collectThumbsUpDomain);
        return collectThumbsUpDomain;
    }
}
