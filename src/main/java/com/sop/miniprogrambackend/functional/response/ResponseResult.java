package com.sop.miniprogrambackend.functional.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 返回给请求方的通用响应结果
 */
@Getter
@Setter
@ToString
public class ResponseResult {
    // 对请求处理后的返回状态：success || failure
    private String status;
    // 当 status = success 时，data 为正确的响应结构体；当 status = failure 时，data 为通用错误代码结构体
    private Object data;

    /**
     * 构造一个发生异常时调用的响应结果
     * @param data
     * @param status
     * @return
     */
    public static ResponseResult generate(Object data, String status) {
        ResponseResult rs = new ResponseResult();
        rs.setData(data);
        rs.setStatus(status);
        return rs;
    }

    /**
     * 方法重载再构造一个正常执行后调用的响应结果
     * @param data
     * @return
     */
    public static ResponseResult generate(Object data) {
        return ResponseResult.generate(data, "success");
    }
}