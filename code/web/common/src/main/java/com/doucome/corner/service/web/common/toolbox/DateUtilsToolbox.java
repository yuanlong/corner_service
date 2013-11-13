package com.doucome.corner.service.web.common.toolbox;

import java.util.Calendar;
import java.util.Date;

import com.doucome.corner.service.biz.core.utils.DateTool;

/**
 * 请直接修改com.doucome.chaoexpo.biz.core.utils.DateTool
 * 
 * @author ib 2012-5-12 下午06:29:25
 */
public class DateUtilsToolbox extends DateTool {
	
	public Date now(){
		return new Date() ;
	}
	
	public long currentTimeMillis(){
		return System.currentTimeMillis() ;
	}
	
	public long timeMillisOf(Date date){
		long millseconds = date == null ? 0 : date.getTime() ;
		return millseconds ;
	}
	
	public boolean isExpired(Date date) {
		Calendar cal = Calendar.getInstance() ;
		Calendar calDate = Calendar.getInstance() ;
		calDate.setTime(date) ;
		return calDate.before(cal) ;
	}
}
