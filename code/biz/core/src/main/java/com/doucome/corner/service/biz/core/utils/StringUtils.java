package com.doucome.corner.service.biz.core.utils;

public class StringUtils {

	/**
	 * 是否是坐标
	 * @param str
	 * @return
	 */
	public static boolean isLocationCode(String str) {
		if (str == null) {
            return false;
        }
        int sz = str.length();
        if(sz <= 0) {
        	return false ;
        }
        for (int i = 0; i < sz; i++) {
        	char c = str.charAt(i) ;
            if (Character.isDigit(c) == false && c != '.') {
                return false ;
            }
        }
        return true;
	}
	
}
