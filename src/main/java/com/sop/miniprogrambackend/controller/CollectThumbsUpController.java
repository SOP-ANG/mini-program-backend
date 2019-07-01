package com.sop.miniprogrambackend.controller;

import com.sop.miniprogrambackend.controller.view.CollectThumbsUpView;
import com.sop.miniprogrambackend.functional.response.ResponseException;
import com.sop.miniprogrambackend.functional.response.ResponseResult;
import com.sop.miniprogrambackend.service.CollectThumbsUpService;
import com.sop.miniprogrambackend.service.domain.CollectThumbsUpDomain;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/collectThumbsUp")
public class CollectThumbsUpController {

    @Autowired
    private CollectThumbsUpService collectThumbsUpService;

    /**
     * 创建会员的集赞活动接口
     * @param file
     * @param userId
     * @param workName
     * @param workProfile
     * @return
     * @throws ResponseException
     */
    @PostMapping("/create")
    public ResponseResult create(@RequestParam(name = "file") MultipartFile file,
                                 @RequestParam(name = "userId") Integer userId,
                                 @RequestParam(name = "workName") String workName,
                                 @RequestParam(name = "workProfile") String workProfile) throws ResponseException {
        this.collectThumbsUpService.create(
                userId, workName, workProfile, this.collectThumbsUpService.uploadImg(file, userId));
        return ResponseResult.generate(null);
    }

    /**
     * 获取集赞列表接口
     * @return
     */
    @GetMapping("/getList")
    public ResponseResult getList() {
        return ResponseResult.generate(this.collectThumbsUpService.getAll().stream().map(
                this::convertFromDomain).collect(Collectors.toList()));
    }

    /**
     * 点赞接口
     * @return
     */
    @PostMapping("/give")
    public ResponseResult give(@RequestBody CollectThumbsUpDomain collectThumbsUpDomain) {
        this.collectThumbsUpService.give(collectThumbsUpDomain);
        return ResponseResult.generate(null);
    }

    private CollectThumbsUpView convertFromDomain(CollectThumbsUpDomain collectThumbsUpDomain) {
        if(collectThumbsUpDomain == null) {
            return null;
        }
        CollectThumbsUpView collectThumbsUpView = new CollectThumbsUpView();
        BeanUtils.copyProperties(collectThumbsUpDomain, collectThumbsUpView);
        return collectThumbsUpView;
    }
}
