package com.doucome.corner.service.biz.core.utils;

public class ChaoUserUtils {
	
	private static final String RANDOM_USER_PREFIX = "user" ;

	/**
	 * 随机产生用户名
	 * @return
	 */
	public static String randomUserName(Long maxId) {
		return RANDOM_USER_PREFIX + maxId ;  
	}
}
