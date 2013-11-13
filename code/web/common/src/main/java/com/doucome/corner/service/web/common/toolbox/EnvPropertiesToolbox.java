package com.doucome.corner.service.web.common.toolbox;



import org.apache.commons.lang.StringUtils;

import com.doucome.corner.service.biz.core.constant.EnvConstant;
import com.doucome.corner.service.biz.core.utils.EnvPropertiesUtil;


/**
 * EnvPropertiesToolbox velocity toolbox
 * @author langben 2011-12-23
 *
 */
public class EnvPropertiesToolbox {
	
	/**
	 * getProperty
	 * @param key
	 * @return
	 */
	public String getProperty(String key){
		return EnvPropertiesUtil.getProperty(key) ;
	}
	
	public String getProp(String key){
		return getProperty(key) ;
	}
	
	public boolean isProduction(){
		String production = EnvPropertiesUtil.getProperty(EnvConstant.CORNERSERVICE_PRODUCTION) ;
		if(StringUtils.equals(production, "true")){
			return true ;
		}
		return false ;
	}
	
//	/**
//	 * 
//	 * @return
//	 */
//	public String getRoot(){
//		return DefaultUriService.getFactoryURI(URIConstant.SERVER) ;
//	}
	
}
