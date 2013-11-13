package com.doucome.corner.service.biz.core.utils;

import java.io.Closeable;

public class StreamUtils {
	/**
	 * 
	 * @param closeable
	 * @return
	 */
	public static boolean close(Closeable closeable) {
		if (closeable == null) {
			return true;
		}
		try {
			closeable.close();
		} catch (Throwable e) {
			
		}
		return true;
	}
}
