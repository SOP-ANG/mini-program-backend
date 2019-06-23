package com.sop.miniprogrambackend.functional.response;

/**
 * 定义异常响应规范
 */
public interface ResponseError {
    public int getErrCode();
    public String getErrMsg();
    public ResponseError setErrMsg(String errMsg);
}
