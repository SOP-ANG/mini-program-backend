package com.sop.miniprogrambackend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sop.miniprogrambackend.service.domain.ClockInDomain;
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
public class ClockInControllerTest extends BaseControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(ClockInControllerTest.class);

    @Test
    public void getCourseList() throws Exception {
        ClockInDomain clockInDomain = new ClockInDomain();
        clockInDomain.setUserId(9);
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/clockIn/getCourseList"
                ).content(
                        JSON.toJSONString(clockInDomain)
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

    @Test
    public void clockInDone() throws Exception {
        ClockInDomain clockInDomain = new ClockInDomain();
        clockInDomain.setUserId(9);
        clockInDomain.setCourseId(1);
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/clockIn/clockInDone"
                ).content(
                        JSON.toJSONString(clockInDomain)
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