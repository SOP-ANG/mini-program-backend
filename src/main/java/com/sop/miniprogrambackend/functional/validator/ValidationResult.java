package com.sop.miniprogrambackend.functional.validator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class ValidationResult {

    // 校验是否通过
    private boolean passed = true;
    // 不通过时，存放错误信息
    private Map<String, String> errMsgMap = new HashMap<>();

    /**
     * 实现通用的通过格式化字符串信息后获取错误结果的方法
     * @return
     */
    public String getErrMsg() {
        return StringUtils.join(errMsgMap.values().toArray(), ", ");
    }
}
