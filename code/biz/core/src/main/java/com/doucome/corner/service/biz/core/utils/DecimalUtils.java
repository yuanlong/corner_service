package com.doucome.corner.service.biz.core.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.service.biz.core.constant.DecimalConstant;

public class DecimalUtils {
	
	public static final MathContext DEFAULT_MATHCONTEXT = MathContext.DECIMAL64 ;
	
	public static BigDecimal parseDecimal(String str , BigDecimal defaultVal) {
		if(StringUtils.isBlank(str)) {
			return defaultVal ;
		}
		try {
			return new BigDecimal(str) ;
		} catch (NumberFormatException e) {
			return defaultVal ;
		}
	}
	
	public static boolean equal(BigDecimal d1 , BigDecimal d2) {
		d1 = nullToZero(d1) ;
		d2 = nullToZero(d2) ;
		return d1.compareTo(d2) == 0 ;
	}
	
	/**
	 * >= 0
	 * @param bd
	 * @return
	 */
	public static boolean isGreaterEqualThan0(BigDecimal d){
		d = nullToZero(d) ;
		int i = d.compareTo(DecimalConstant.ZERO) ;
		return i >= 0 ;
	}
	
	/**
	 * > 0
	 * @param bd
	 * @return
	 */
	public static boolean isGreaterThan0(BigDecimal d){
		d = nullToZero(d) ;
		int i = d.compareTo(DecimalConstant.ZERO) ;
		return i > 0 ;
	}
	
	public static String format(BigDecimal d , String format){
		d = nullToZero(d) ;
		DecimalFormat f = new DecimalFormat(format);
        return f.format(d);
	}

	/**
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean greatThan(BigDecimal d1 , BigDecimal d2){
		d1 = nullToZero(d1) ;
		d2 = nullToZero(d2) ;
		int i = d1.compareTo(d2) ;
		return i == 1 ;
	}
	
	/**
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean lessThan(BigDecimal d1 , BigDecimal d2){
		d1 = nullToZero(d1) ;
		d2 = nullToZero(d2) ;
		int i = d1.compareTo(d2) ;
		return i == -1 ;
	}
	
	/**
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean greatEqualThan(BigDecimal d1 , BigDecimal d2){
		d1 = nullToZero(d1) ;
		d2 = nullToZero(d2) ;
		int i = d1.compareTo(d2) ;
		return i == -1 ? false : true ;
	}
	
	/**
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean lessEqualThan(BigDecimal d1 , BigDecimal d2){
		d1 = nullToZero(d1) ;
		d2 = nullToZero(d2) ;
		int i = d1.compareTo(d2) ;
		return i == 1 ? false : true ;
	}
	
	/**
	 * multiply
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal d1 , BigDecimal d2){
		d1 = nullToZero(d1) ;
		d2 = nullToZero(d2) ;
		return d1.multiply(d2) ;
	}
	
	public static BigDecimal divide(BigDecimal d1 , BigDecimal d2){
		return divide(d1, d2, DEFAULT_MATHCONTEXT) ;
	}
	
	public static BigDecimal divide(BigDecimal d1 , BigDecimal d2 , MathContext mc){
		d1 = nullToZero(d1) ;
		d2 = nullToZero(d2) ;
		if(mc == null) {
			return d1.divide(d2) ;
		} else {
			return d1.divide(d2 , mc) ;
		}
	}
	
	public static BigDecimal substract(BigDecimal d1 , BigDecimal d2){
		d1 = nullToZero(d1) ;
		d2 = nullToZero(d2) ;
		return d1.subtract(d2) ;
	}

	public static BigDecimal add(BigDecimal d1 , BigDecimal d2){
		d1 = nullToZero(d1) ;
		d2 = nullToZero(d2) ;
		return d1.add(d2) ;
	}
	
	public static BigDecimal abs(BigDecimal d) {
		d = nullToZero(d) ;
		return d.abs() ;
	}
	
	public static BigDecimal nullToZero(BigDecimal d) {
		if(d == null) {
			return new BigDecimal(0) ;
		}
		return d ;
	}
}
