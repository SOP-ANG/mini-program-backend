package com.sop.miniprogrambackend.functional.response;

/**
 * 响应异常枚举类
 */
public enum EnumResponseError implements ResponseError {
    // 10000 开头为通用错误类型
    UNKNOWN_ERROR(10001, "未知错误"),
    DATA_VALIDATION_ERROR(10002, "参数不合法"),
    DATA_CONVERT_ERROR(10003, "数据转换错误"),
    REQUEST_ERROR(10004, "请求失败")

    // 20000 开头为用户信息相关错误
    ;

    private int errCode;
    private String errMsg;

    /**
     * 设置为私有构造方法，只允许枚举对象使用此构造方法构造出一个实现了 {@link ResponseError} 接口的子类，这个子类是一个 Enum 类型
     * @param errCode
     * @param errMsg
     */
    private EnumResponseError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    /**
     * 用于通用错误类型，能够自定义错误描述
     *      如：PARAMETER_VALIDATION_ERROR 是一个通用错误类型 > 参数不合法，它可以是年级没选、code 没传等等
     *      这时，只需要用一个 00001 + 自定义的错误描述，即可表示"参数不合法"这一类错误描述
     * @param errMsg
     * @return
     */
    @Override
    public ResponseError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
