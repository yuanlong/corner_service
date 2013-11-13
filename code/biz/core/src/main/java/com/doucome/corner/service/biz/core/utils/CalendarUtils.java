package com.doucome.corner.service.biz.core.utils;

import java.util.Calendar;

public class CalendarUtils {
	
	public static String format00(int i){
		if(i >= 10){
			return String.valueOf(i) ;
		}
		return "0" + i ;
	}

	public static int getYear(Calendar cal){
		if(cal == null){
			return 0 ;
		}
		return cal.get(Calendar.YEAR) ;
	}
	
	public static int getMonth(Calendar cal){
		if(cal == null){
			return 0 ;
		}
		return cal.get(Calendar.MONTH);
	}
	
	public static int getDay(Calendar cal){
		if(cal == null){
			return 0 ;
		}
		return cal.get(Calendar.DATE) ;
	}
	
	public static int getHour(Calendar cal){
		if(cal == null){
			return 0 ;
		}
		return cal.get(Calendar.HOUR_OF_DAY) ;
	}
	
	public static int getHour12(Calendar cal){
		if(cal == null){
			return 0 ;
		}
		return cal.get(Calendar.HOUR) ;
	}
	
	public static int getMinute(Calendar cal){
		if(cal == null){
			return 0 ;
		}
		return cal.get(Calendar.MINUTE) ;
	}
	
	public static int getSecond(Calendar cal){
		if(cal == null){
			return 0 ;
		}
		return cal.get(Calendar.SECOND) ;
	}
	
	public static int getMills(Calendar cal){
		if(cal == null){
			return 0 ;
		}
		return cal.get(Calendar.MILLISECOND) ;
	}
	
	private static final int[] CALENDAR_FIELDS = {Calendar.MILLISECOND, Calendar.SECOND, Calendar.MINUTE,
		  Calendar.HOUR_OF_DAY, Calendar.DAY_OF_MONTH};
	
	public static boolean trimTo(Calendar calendar, int calendarField) {
		for (int field: CALENDAR_FIELDS) {
			calendar.set(field, 0);
			if (field == calendarField) {
				return true;
			}
		}
		return false;
	}
}
