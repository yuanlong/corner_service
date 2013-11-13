package com.doucome.corner.service.biz.core.utils;

import java.util.List;

import com.doucome.corner.service.biz.common.utils.ReflectUtils;

public final class ArrayUtil {
	/**
	 * 
	 * @param <T>
	 * @param objects
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public static <T> boolean batchSetField(T[] objects, String fieldName, Object value) {
		if (objects == null || objects.length == 0) {
			return true;
		}
		boolean result = false;
		for (T temp : objects) {
			result = ReflectUtils.setField(temp, fieldName, value);
		}
		return result;
	}
	
	
}
