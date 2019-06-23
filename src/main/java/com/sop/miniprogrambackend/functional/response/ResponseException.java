package com.sop.miniprogrambackend.functional.response;

/**
 * 设计模式：包装器业务异常类实现
 * {@link ResponseException} 和 {@link EnumResponseError} 共同实现了 {@link ResponseError} 接口
 *
 * 外部不仅可以通过 new 一个 {@link EnumResponseError}，
 * 或者 new 一个 {@link ResponseException} 都可以有 errCode 和 errMsg 对应的组装定义，
 * 并且共同实现 setErrMsg 方法，可以将原本 Enum 类中定义的 errMsg 覆盖
 */
public class ResponseException extends Exception implements ResponseError {

    // 强关联实现了 ResponseError 的 Enum 类
    private ResponseError enumResponseError;

    /**
     * 使用 Enum 类中的原始定义构造异常响应
     * 构造方法中需要调用一下 super 方法，{@link Exception} 自身会有一些初始化机制
     * @param enumResponseError
     */
    public ResponseException(ResponseError enumResponseError) {
        super();
        this.enumResponseError = enumResponseError;
    }

    /**
     * 接收自定义 errMsg 构造异常响应
     * 构造方法中需要调用一下 super 方法，{@link Exception} 自身会有一些初始化机制
     * @param errMsg
     * @param enumResponseError
     */
    public ResponseException(String errMsg, ResponseError enumResponseError) {
        super(errMsg);
        this.enumResponseError = enumResponseError;
        this.enumResponseError.setErrMsg(errMsg);
    }

    @Override
    public int getErrCode() {
        return this.enumResponseError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.enumResponseError.getErrMsg();
    }

    @Override
    public ResponseError setErrMsg(String errMsg) {
        this.enumResponseError.setErrMsg(errMsg);
        return this;
    }
}
