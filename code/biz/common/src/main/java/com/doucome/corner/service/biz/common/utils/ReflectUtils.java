package com.doucome.corner.service.biz.common.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;

public class ReflectUtils {

	public static <T> void reflectTo(T src , T target){
		if(target == null || src == null){
			return ;
		}
		
		Class<?> clazz = target.getClass() ;
		Field[] fields = src.getClass().getDeclaredFields() ;
		for(Field field : fields){
			String fieldName = field.getName() ;
			Field targetField = ReflectionUtils.findField(clazz, fieldName) ;
			if(targetField == null){
				continue ;
			}
			PropertyDescriptor sourceDescriptor = null ;
			try {
				sourceDescriptor = new PropertyDescriptor(fieldName,src.getClass());
			} catch (IntrospectionException e) {
				continue ;
			}
			Method getMethod = sourceDescriptor.getReadMethod();//获得get方法
			Object sourceValue = ReflectionUtils.invokeMethod(getMethod, src) ;
			if(sourceValue == null){
				continue ;
			}
			Class<?> targetType = targetField.getType() ;
			Class<?> sourceType = field.getType() ;
			PropertyDescriptor targetDescriptor = null ;
			try {
				targetDescriptor = new PropertyDescriptor(fieldName, target.getClass()) ;
			} catch (IntrospectionException e) {
				fieldName = uppperCaseFeildName(fieldName);
				try {
					targetDescriptor = new PropertyDescriptor(fieldName, target.getClass()) ;
				} catch (Exception e1) {
				}
				continue ;
			}
			Method setMethod = targetDescriptor.getWriteMethod() ;
			if(setMethod != null){
				if(targetType.equals(sourceType)){
					ReflectionUtils.invokeMethod(setMethod, target, sourceValue) ;
				}else if(targetType.equals(String.class)){
					ReflectionUtils.invokeMethod(setMethod, target, String.valueOf(sourceValue)) ;
				}else if(targetType.equals(BigDecimal.class)){
					ReflectionUtils.invokeMethod(setMethod , target , new BigDecimal(String.valueOf(sourceValue))) ;
				}else{
					//未知
				}
			}
		}
	}
	
	private static String uppperCaseFeildName(String fieldName) {
		String temp = fieldName;
		int index = -1;
		while((index = temp.indexOf("_")) != -1 && index + 2 < temp.length()) {
			if (index == 0) {
				temp = temp.replaceFirst("_", "");
			} else {
				String replace = temp.substring(index + 1, index + 2);
				temp = temp.replaceAll("_" + replace, replace.toUpperCase());
			}
		}
		return temp;
	}
	
	public static <T> boolean setField(T clazz, String fieldName, Object value) {
		PropertyDescriptor descriptor = null;
		try {
			descriptor = new PropertyDescriptor(fieldName, clazz.getClass());
		} catch (IntrospectionException e) {
			return false;
		}
		Method method = descriptor.getWriteMethod();
		try {
			ReflectionUtils.invokeMethod(method, clazz, value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static <T> Object getFiled(T clazz, String fieldName) {
		PropertyDescriptor descriptor = null;
		try {
			descriptor = new PropertyDescriptor(fieldName, clazz.getClass());
		} catch (IntrospectionException e) {
			return null;
		}
		Method method = descriptor.getReadMethod();
		try {
			return ReflectionUtils.invokeMethod(method, clazz);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static <T> void mergeObject(T mergeFrom, T mergeTo) {
		if (mergeFrom == null) {
			return ;
		}
		if (mergeTo == null) {
			mergeTo = mergeFrom;
			return;
		}
		Class<?> toClazz = mergeTo.getClass() ;
		Field[] fields = toClazz.getDeclaredFields();
		Object tempValue = null;
		for (Field field: fields) {
			try {
				tempValue = getFiled(mergeFrom, field.getName());
				if (tempValue != null) {
					if (field.getType().equals(String.class)) {
						if (StringUtils.isEmpty((String)tempValue)) {
							continue;
						}
					}
					setField(mergeTo, field.getName(), tempValue);
				}
			} catch (Exception e) {
				
			}
		}
	}
}
