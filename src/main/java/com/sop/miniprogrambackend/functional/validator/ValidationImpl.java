package com.sop.miniprogrambackend.functional.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 1. 配置成一个 Spring Bean，让 Spring 在类扫描的时候能够扫描到
 * 2. 实现 InitializingBean 接口，用于在 bean 初始化完成后回调 ValidatorImpl.afterPropertiesSet
 * 3. 为了包装 javax.validation.Validator，它是通过 javax 定义实现的一套工具
 */
@Component
public class ValidationImpl implements InitializingBean {

    private Validator validator;

    @Override
    public void afterPropertiesSet() throws Exception {
        //将 hibernate validator 通过工厂的初始化方式使其实例化
        //通过 defaultValidatorFactory build 了一个实现了 javax validator 的校验器，也就是 hibernate validator
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    //实现校验方法并返回校验结果
    public ValidationResult validate(Object bean) {
        final ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> constraintViolationSet = this.validator.validate(bean);
        if(constraintViolationSet.size() > 0) {
            result.setPassed(false);
            constraintViolationSet.forEach(constraintViolation -> {
                String propertyName = constraintViolation.getPropertyPath().toString();
                String errMsg = constraintViolation.getMessage();
                result.getErrMsgMap().put(propertyName, errMsg);
            });
        }
        return result;
    }
}
