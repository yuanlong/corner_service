package com.doucome.corner.service.biz.core.bo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 
 * @author ze2200
 *
 */
public class DdzAlimamaRefreshCron {
	
	private static final Log alimamaLogger = LogFactory.getLog("alimama-log");
	
	private static final String REFRESH_DEST_URL = "http://u.alimama.com/membersvc/index.htm";
	
	@Autowired
	private DdzAlimamaBO ddzAlimamaBO;
	
	public void refresh() {
		try {
			ddzAlimamaBO.refreshLoginStatus();
		} catch (Exception e) {
			alimamaLogger.error(e);
		}
	}
}
