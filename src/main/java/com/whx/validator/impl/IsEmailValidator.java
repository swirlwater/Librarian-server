package com.whx.validator.impl;

import com.whx.utils.ValidatorUtil;
import com.whx.validator.IsEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 邮箱校验器
 */
public class IsEmailValidator implements ConstraintValidator<IsEmail,String> {

    private boolean required=false;

    @Override
    public void initialize(IsEmail constraintAnnotation) {
        required= constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (required){
            return ValidatorUtil.isEmail(value);
        }
        return true;
    }
}
