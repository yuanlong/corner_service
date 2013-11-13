package com.doucome.corner.service.biz.core.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 类MobileUtil.java的实现描述：手机号码相关工具类
 * 
 * @author ib 2012-5-8 上午01:40:37
 */
public class MobileUtil {

    private static final String PRIVATE_STRING = "*****";
    private static final String PRIVATE_STRING_SHORT = "***";

    public static String getPrivateMobile(String mobile) {
        if (mobile == null) {
            return null;
        }
        if (mobile.length() < 6) {
            return mobile;
        }
        String prefix = StringUtils.substring(mobile, 0, 3);
        String suffix = StringUtils.substring(mobile, mobile.length() - 3);
        return prefix + PRIVATE_STRING + suffix;
    }
    
    public static String getPrivateMobileForShort(String mobile){
    	if (mobile == null) {
            return null;
        }
        if (mobile.length() < 6) {
            return mobile;
        }
        String prefix = StringUtils.substring(mobile, 0, 3);
        String suffix = StringUtils.substring(mobile, mobile.length() - 3);
        return prefix + PRIVATE_STRING_SHORT + suffix;
    }
    
    public static void main(String[] args) {
        System.out.println(getPrivateMobile("12"));
        System.out.println(getPrivateMobile("12345"));
        System.out.println(getPrivateMobile("123456"));
        System.out.println(getPrivateMobileForShort("13356789098"));
    }
}
