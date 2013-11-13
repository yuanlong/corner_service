package com.doucome.corner.service.biz.core.utils;

public class ChaoDisplayOrderUtils {
	/**
	 * 
	 * @return
	 */
	public static long getDisplayOrder() {
		return System.currentTimeMillis() / 100l;  
	}
}
