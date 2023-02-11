package com.whx.validator.impl;

import com.whx.utils.ValidatorUtil;
import com.whx.validator.IsMobile;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean required=false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required= constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (required){
            return ValidatorUtil.isMobile(value);
        }else{
            if (StringUtils.hasLength(value)){
                return false;
            }else{
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}
