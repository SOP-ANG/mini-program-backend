package com.sop.miniprogrambackend.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sop.miniprogrambackend.service.domain.UserDomain;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserControllerTest extends BaseControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    @Test
    public void register() throws Exception {
        UserDomain userDomain = new UserDomain();
        userDomain.setId(9);
        userDomain.setNickName("test");
        userDomain.setDistrict("五华区");
        userDomain.setSchool("盘龙小学");
        userDomain.setGrade("一年级");
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post(
                        "/user/register"
                ).content(
                        JSON.toJSONString(userDomain)
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