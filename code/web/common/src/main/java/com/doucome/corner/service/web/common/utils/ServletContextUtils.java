package com.doucome.corner.service.web.common.utils;

import org.apache.commons.lang3.StringUtils;

public class ServletContextUtils {

	private static String rootRealPath ;
	
	public static void setRootRealPath(String realPath) {
		rootRealPath = realPath ;
	}
	
	public static String getRootRealPath() {
		return rootRealPath ;
	}
	
	public static String getRealPath(String path) {
		if(StringUtils.startsWith(path	, "/")){
			return rootRealPath + path ;
		} 
		return rootRealPath + "/" + path ;
	}
}
