package com.sop.miniprogrambackend.controller;

import com.sop.miniprogrambackend.service.BackendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class BackendController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BackendController.class);

    @Autowired
    private BackendService backendService;

    /**
     * 获得会员数和打卡满 28 次会员数
     * @return
     */
    @RequestMapping("/")
    public String index(Map<String, Object> paramMap) {
        paramMap = backendService.getIndexData();
        return "index";
    }
}
