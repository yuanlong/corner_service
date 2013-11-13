package com.doucome.corner.service.biz.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * SearchCondition utils
 * @author 
 *
 */
public final class ConditionUtils {
	
	public static Date[] getDayStartEnd(Date gmtDay) {
		if (gmtDay == null) {
			return new Date[2];
		}
		Date[] dates = new Date[2];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(gmtDay);
		calendar = org.apache.commons.lang.time.DateUtils.truncate(calendar, Calendar.DAY_OF_MONTH);
		dates[0] = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MILLISECOND, -1);
		dates[1] = calendar.getTime();
		return dates;
	}
}
