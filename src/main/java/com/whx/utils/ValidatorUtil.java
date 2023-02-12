package com.whx.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {

    public static final Pattern mobile_pattern=Pattern.compile("^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$");

    public static final Pattern email_pattern=Pattern.compile("^([A-z0-9]{6,18})([w\\-])+@[A-z0-9]+.([A-z]{2,3})$");

    public static boolean isMobile(String mobile){
        if (!StringUtils.hasLength(mobile)){
            return false;
        }
        Matcher matcher = mobile_pattern.matcher(mobile);
        return matcher.matches();
    }

    public static boolean isEmail(String email){
        if (!StringUtils.hasLength(email)){
            return false;
        }
        Matcher matcher = email_pattern.matcher(email);
        return matcher.matches();
    }
}
