package com.sop.miniprogrambackend.controller;

import com.sop.miniprogrambackend.functional.response.ResponseResult;
import com.sop.miniprogrambackend.service.domain.CollectThumbsUpDomain;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collectThumbsUp")
public class CollectThumbsUpController {

    /**
     *
     * @param collectThumbsUpDomain
     * @return
     */
    @PostMapping("/create")
    public ResponseResult create(@RequestBody CollectThumbsUpDomain collectThumbsUpDomain) {

        return ResponseResult.generate(null);
    }
}
