package com.whx.utils;

public class CaptchaUtil {
    public static String generatedCode(int count){
        return String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, count - 1)));
    }
}
