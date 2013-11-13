package com.doucome.corner.service.biz.core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_2MM     = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_DAY     = "yyyy-MM-dd";

    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        DateFormat f = new SimpleDateFormat(format);
        return f.format(date);
    }

    /**
     * yyyy-MM-dd HH:mm:ss格式
     * 
     * @param date
     * @return
     */
    public static String defaultFormat(Date date) {
        return format(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * yyyy-MM-dd格式.
     * @param date
     * @return
     */
    public static String formatToDay(Date date) {
    	return format(date, DATE_FORMAT_DAY);
    }
    
    /**
     * yyyy-MM-dd HH:mm格式
     * 
     * @param date
     * @return
     */
    public static String format_2mm(Date date) {
        return format(date, DATE_FORMAT_2MM);
    }
    
    public static long getMilliseconds(Date date){
    	if(date == null){
    		return 0 ;
    	}
    	return date.getTime() ;
    }
}
