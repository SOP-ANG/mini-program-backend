package com.sop.miniprogrambackend.functional.utils;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于访问外部接口
 * 解决微信的奇葩问题：处理返回的数据明明是 json 格式，而 content-type 却给的是 text/plain 的奇葩响应数据
 */
public class RestTemplateClient {

    public static RestTemplate getInstance() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // 获取原先支持的 MediaTypes
        List<MediaType> mediaTypeList = new ArrayList<>(converter.getSupportedMediaTypes());
        // 加入 TEXT_PLAIN 的支持
        mediaTypeList.add(MediaType.TEXT_PLAIN);
        // 塞回去
        converter.setSupportedMediaTypes(mediaTypeList);
        restTemplate.getMessageConverters().add(converter);
        return restTemplate;
    }
}
