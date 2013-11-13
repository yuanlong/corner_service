package com.doucome.corner.service.web.common.toolbox;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;



public class StaticVersionToolbox {

	private static final Log logger = LogFactory.getLog(StaticVersionToolbox.class) ;
	
	private static final String PROPERTIES_PATH = "static-version.properties" ;
	
	private static final String DEFAULT = "default" ;
	
	private static Properties props = null ; 
	
	private static Lock mutex = new ReentrantLock() ;
	
	/**
	 * 从PROPERTIES_PATH获取版本号
	 * @param path
	 * @return
	 */
	public String ver(String path){
		if(StringUtils.isBlank(path)) {
			return getDefaultVersion() ;
		}
		String version = getProperty(path) ;
		if(StringUtils.isBlank(version)){
			return getDefaultVersion() ;
		}
		return version ;
		
	}

	private String getDefaultVersion(){
		String version = getProperty(DEFAULT) ;
		if(StringUtils.isBlank(version)){
			return "19700101" ;
		}
		return version ;
	}
	
	/**
	 *
	 * @param key
	 * @return
	 */
	private static String getProperty(String key){
		if(props == null){
			mutex.lock();
			try{
				if(props == null){
					props = loadPropertiesFromPath(PROPERTIES_PATH) ;
					if(props == null){
						logger.error("cant load properties file :" + PROPERTIES_PATH) ;
						return null ;
					}
				}
			}finally{
				mutex.unlock() ;
			}
		}
		
		return props.getProperty(key) ;
	}
	
	//
	private static Properties loadPropertiesFromPath(String path) {
		Properties p = new Properties();
		InputStream in = null;
		try {
			Resource resource = new ClassPathResource(PROPERTIES_PATH) ;
			in = resource.getInputStream() ;
			p.load(in);
		} catch (Exception e) {
			logger.error("Error reading env.properties:" + e.getMessage(), e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception ex) {
					logger.error("Error reading env.properties:" +  ex.getMessage(), ex);
				}
			}
		}
		return p ;
	}
}
