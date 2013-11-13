package com.doucome.corner.service.biz.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * Array =&gt; String 或者 String =&gt; Array
 * @author langben 2012-2-24
 *
 */
public class ArrayStringUtils {
	
	

	/**
	 * String以","为分隔成Array
	 * @param str
	 * @return
	 */
	public static String[] toArray(String str){
		String[] arr = StringUtils.split(str, ",") ;
		return arr ;
	}
	
	/**
	 * 将字符串数组转成","链接的字符串
	 * @param arr
	 * @return
	 */
	public static String toString(String[] arr){
		if(arr == null || arr.length == 0){
			return "" ;
		}
		
		StringBuilder strConcat = new StringBuilder() ; 
		for(int i=0 ;i<arr.length;i++){
			String str = arr[i] ;
			strConcat.append(str) ;
			if(i != arr.length-1){
				strConcat.append(",") ;
			}
		}
		return strConcat.toString() ;
	}
	
	public static String toString(Long[] arr){
		if(arr == null || arr.length == 0){
			return "" ;
		}
		
		StringBuilder strConcat = new StringBuilder() ; 
		for(int i=0 ;i<arr.length;i++){
			Long str = arr[i] ;
			strConcat.append(str) ;
			if(i != arr.length-1){
				strConcat.append(",") ;
			}
		}
		return strConcat.toString() ;
	}
	
	public static List<String> toList(String str){
		String[] arr = toArray(str) ;
		if(ArrayUtils.isEmpty(arr)){
			return new ArrayList<String>() ;
		}
		List<String> list = new ArrayList<String>() ;
		for(String s : arr){
			if(StringUtils.isNotBlank(s)){
				list.add(s) ;
			}
		}
		return list ;
	}
	
	public static List<Long> toLongList(String str){
		String[] arr = toArray(str) ;
		if(ArrayUtils.isEmpty(arr)){
			return new ArrayList<Long>() ;
		}
		List<Long> list = new ArrayList<Long>() ;
		for(String s : arr){
			if(StringUtils.isNotBlank(s) && StringUtils.isNumeric(s)){
				list.add(Long.valueOf(s)) ;
			}
		}
		return list ;
	}
	
	
	public static String toString(@SuppressWarnings("rawtypes") List list){
		return toString(list, ",");
	}
	
	public static String toString(List<?> list, String sep) {
		if(CollectionUtils.isEmpty(list)){
			return "" ;
		}
		StringBuilder strConcat = new StringBuilder() ; 
		for(int i=0 ;i<list.size();i++){
			Object obj = list.get(i) ;
			strConcat.append(obj) ;
			if(i != list.size()-1){
				strConcat.append(sep) ;
			}
		}
		return strConcat.toString() ;
	}
	
	public static Long[] toLongArray(List<Long> list) {
		if(list == null){
			return new Long[] {};
		}
		
		return (Long[])list.toArray(new Long[list.size()]) ;
	}
	
	public static String[] toStringArray(List<String> list) {
		if(list == null){
			return new String[] {};
		}
		return list.toArray(new String[]{}) ;
	}
}
