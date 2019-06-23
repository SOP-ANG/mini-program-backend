package com.sop.miniprogrambackend.functional.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * 自定义响应结构， 转换类
 */
public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 对象转 json 字符串
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public static String pojoToJson(Object obj) throws JsonProcessingException {
        return MAPPER.writeValueAsString(obj);
    }

    /**
     * json 字符串转对象
     * @param jsonStr
     * @param beanType  对象类型
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T jsonToPojo(String jsonStr, Class<T> beanType) throws IOException {
        return MAPPER.readValue(jsonStr, beanType);
    }

    /**
     * json 字符串转对象 list
     * @param jsonStr
     * @param beanType
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T>List<T> jsonToPojoList(String jsonStr, Class<T> beanType) throws IOException {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        return MAPPER.readValue(jsonStr, javaType);
    }

}
