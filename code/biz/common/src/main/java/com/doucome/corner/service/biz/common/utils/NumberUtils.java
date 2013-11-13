package com.doucome.corner.service.biz.common.utils;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

public class NumberUtils {

	/**
	 * 
	 * @param i
	 * @return
	 */
	public static int parseInt (Integer i){
		if(i == null){
			return 0 ;
		}
		return i ;
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 */
	public static int parseInt (String i){
		if(StringUtils.isBlank(i)){
			return 0 ;
		}
		if(!StringUtils.isNumeric(i)){
			return 0 ;
		}
		return Integer.parseInt(i) ;
	}
	
	public static long parseLong(Long i){
		if(i == null){
			return 0 ;
		}
		return i ;
	}
	
	public static boolean isGreaterEqual(BigDecimal decimal1 , BigDecimal decimal2){
		if(decimal1 == null){
			return false ;
		}
		if(decimal2 == null){
			return true ;
		}
		int compareVal = decimal1.compareTo(decimal2) ;
		return compareVal == -1 ? false : true ;
	}
	
	public static boolean isLessEqual(BigDecimal decimal1 , BigDecimal decimal2){
		if(decimal1 == null){
			return true ;
		}
		if(decimal2 == null){
			return false ;
		}
		int compareVal = decimal1.compareTo(decimal2) ;
		return compareVal == 1 ? false : true ;
	}
	
	public static Integer substract(Integer i1 , Integer i2) {
		i1 = parseInt(i1) ;
		i2 = parseInt(i2) ;
		return i1 - i2 ;
		
	}
	
	public static Integer add(Integer i1 , Integer i2) {
		i1 = parseInt(i1) ;
		i2 = parseInt(i2) ;
		return i1 + i2 ;
		
	}
	
	public static boolean equals(Long l1 , Long l2) {
		if(l1 == null && l2 == null) {
			return true ;
		}
		if(l1 == null || l2 == null) {
			return false ;
		}
		
		return l1.longValue() == l2.longValue() ;
	}
}
