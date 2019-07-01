package com.sop.miniprogrambackend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sop.miniprogrambackend.service.domain.CollectThumbsUpDomain;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CollectThumbsUpControllerTest extends BaseControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(CollectThumbsUpControllerTest.class);

    @Test
    public void give() throws Exception {
        CollectThumbsUpDomain collectThumbsUpDomain = new CollectThumbsUpDomain();
        collectThumbsUpDomain.setId(2);
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/collectThumbsUp/give"
                ).content(
                        JSON.toJSONString(collectThumbsUpDomain)
                ).contentType(
                        MediaType.APPLICATION_JSON
                )
        ).andReturn();
        Assert.assertEquals(mvcResult.getResponse().getStatus(), 200);
        JSONObject jsonObject = JSON.parseObject(mvcResult.getResponse().getContentAsString());
        Assert.assertEquals(jsonObject.get("status"), "success");
        if(jsonObject.get("data") != null) {
            logger.info(jsonObject.get("data").toString());
        }
    }
}