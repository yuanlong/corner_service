package com.doucome.corner.service.biz.core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author langben 2012-4-12
 * 
 */
public class DateUtils {

	private static final int[] CALENDAR_FIELD_LIST = { Calendar.MILLISECOND,
			Calendar.SECOND, Calendar.MINUTE, Calendar.HOUR_OF_DAY,
			Calendar.DAY_OF_MONTH, Calendar.MONTH };

	public static Date setTime(Date date, int h, int m, int s) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, h);
		c.set(Calendar.MINUTE, m);
		c.set(Calendar.SECOND, s);
		return c.getTime();
	}
	
	public static Date setDate(Date date , int year , int month , int day) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DATE, day);
		return c.getTime();
	}

	public static Date trimDate(Date date, int calendarField) {
		if (date == null) {
			return date;
		}
		Calendar temp = Calendar.getInstance();
		temp.setTime(date);
		for (int field : CALENDAR_FIELD_LIST) {
			if (field == Calendar.DAY_OF_MONTH || field == Calendar.MONDAY) {
				temp.set(field, 1);
			} else {
				temp.set(field, 0);
			}
			if (field == calendarField) {
				return temp.getTime();
			}
		}

		return date;
	}

	public static Calendar getCalendar(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}
	
	/**
	 * 获取date nDay天前的时间.
	 * @param date
	 * @param nDay
	 * @return
	 */
	public static Date getNDayBeforeTime(Date date, int nDay) {
		Calendar calendar = getCalendar(date);
		calendar.add(Calendar.DAY_OF_YEAR, -1 * nDay);
		return calendar.getTime();
	}
	/**
	 * 时间是否在区间start 和 end 之间
	 * 
	 * @param start
	 * @param end
	 * @param date
	 * @return
	 */
	public static boolean isBetween(Date date , Date start, Date end) {
		if (start == null || end == null || date == null) {
			return false;
		}
		if (date.after(end)) {
			return false;
		}
		if (date.before(start)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param day
	 * @return
	 */
	public static boolean isYesterday(Date day) {
		if (day == null) {
			return false;
		}
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DAY_OF_YEAR, -1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(day);
		return (calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR))
				&& (calendar1.get(Calendar.DAY_OF_YEAR) == calendar2
						.get(Calendar.DAY_OF_YEAR));
	}

	public static boolean isSameDay(Date day1, Date day2) {
		if (day1 == null || day2 == null) {
			return false;
		}
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(day1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(day2);
		return (calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR))
				&& (calendar1.get(Calendar.DAY_OF_YEAR) == calendar2
						.get(Calendar.DAY_OF_YEAR));
	}

	public static boolean isToday(Date date) {
		if (date == null) {
			return false;
		}
		return isSameDay(new Date(), date);
	}

	public static long getDiffInMinute(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return -1l;
		}
		long diff = Math.abs(date1.getTime() - date2.getTime());
		return diff / 60000l;
	}

	public static int getDiffInDays(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return -1;
		}
		Date temp1 = trimDate(date1, Calendar.HOUR_OF_DAY);
		Date temp2 = trimDate(date2, Calendar.HOUR_OF_DAY);
		long count = Math.abs(temp1.getTime() - temp2.getTime());
		return (int) (count / (24l * 60l * 60l * 1000l));
	}

	public static Date[] getDayStartEnd(Date time) {
		if (time == null) {
			return new Date[2];
		}
		Date[] dates = new Date[2];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar = org.apache.commons.lang.time.DateUtils.truncate(calendar,
				Calendar.DAY_OF_MONTH);
		dates[0] = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MILLISECOND, -1);
		dates[1] = calendar.getTime();
		return dates;
	}

	public static Date[] getWeekStartEnd(Date time) {
		Date[] dates = new Date[2];
		if (time == null) {
			return dates;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar = org.apache.commons.lang.time.DateUtils.truncate(calendar,
				Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		dates[0] = calendar.getTime();
		calendar.add(Calendar.WEEK_OF_YEAR, 1);
		calendar.add(Calendar.MILLISECOND, -1);
		dates[1] = calendar.getTime();
		return dates;
	}

	public static Date[] getMonthStartEnd(Date time) {
		if (time == null) {
			return new Date[2];
		}
		Date[] dates = new Date[2];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar = org.apache.commons.lang.time.DateUtils.truncate(calendar,
				Calendar.MONDAY);
		dates[0] = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.MILLISECOND, -1);
		dates[1] = calendar.getTime();
		return dates;
	}
	
	public static boolean isSameMonth(Date date1 , Date date2){
		if(date1 == null || date2 == null){
			return false ;
		}
		
		int year1 = date1.getYear() ;
		int year2 = date2.getYear() ;
		
		int month1 = date1.getMonth() ;
		int month2 = date2.getMonth() ;
		if(year1 == year2 && month1 == month2){
			return true ;
		}
		return false ;
		
	}
	
	public static boolean isBeforeNow(Date date) {
		if(date == null){
			return false ;
		}
		Date dateNow = new Date() ;
		return date.before(dateNow) ;
	}
	
	public static boolean isAfterNow(Date date) {
		if(date == null){
			return false ;
		}
		Date dateNow = new Date() ;
		return date.after(dateNow) ;
	}
	
	public static Date parse(String dateStr, String format) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.parse(dateStr);
		} catch (Exception e) {
			return null;
		}
	}
}
