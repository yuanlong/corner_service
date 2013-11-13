package com.doucome.corner.service.biz.core.utils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.service.biz.core.constant.Constant;
import com.doucome.corner.service.biz.core.model.URLModel;


/**
 * 
 * @author langben 2012-2-25
 * 
 */
public class HttpUrlHelper {

	private static final Log logger = LogFactory.getLog(HttpUrlHelper.class);

	/**
	 * @param httpUrl
	 * @return
	 */
	public static URLModel parseURL(String url) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		
		URLModel model = new URLModel();
		if(!StringUtils.startsWithIgnoreCase(url, Constant.HTTP)){
			if(StringUtils.lastIndexOf(url, ".") == StringUtils.INDEX_NOT_FOUND){
				return null ;
			}
			url = Constant.HTTP + "://" + url ;
		}
		
		try {
			
			URL aURL = new URL(url) ;
			
			model.setHost(aURL.getHost());
			model.setProtocol(aURL.getProtocol());
			model.setPort(aURL.getPort());
			model.setPath(aURL.getPath()) ;
			//String encode = aURL.get
			if (StringUtils.isNotBlank(aURL.getQuery())) {
				String queryString = StringUtils.trim(aURL.getQuery());
				Map<String, String> params = getParamsMap(queryString,Constant.ENCODING);
				model.setParams(params);
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("cant parse uri " + url + ",msg:" + e.getMessage());
			}
			return null;
		}

		return model;

	}

	private static Map<String, String> getParamsMap(String queryString,
			String enc) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		if (queryString != null && queryString.length() > 0) {
			int ampersandIndex, lastAmpersandIndex = 0;
			String subStr, param, value;
			String[] paramPair;
			do {
				ampersandIndex = queryString.indexOf('&', lastAmpersandIndex) + 1;
				if (ampersandIndex > 0) {
					subStr = queryString.substring(lastAmpersandIndex,
							ampersandIndex - 1);
					lastAmpersandIndex = ampersandIndex;
				} else {
					subStr = queryString.substring(lastAmpersandIndex);
				}

				paramPair = subStr.split("=");
				param = paramPair[0];
				value = paramPair.length == 1 ? "" : paramPair[1];
				try {
					value = URLDecoder.decode(value, enc);
				} catch (UnsupportedEncodingException ignored) {

				}

				paramsMap.put(param, value);
			} while (ampersandIndex > 0);
		}
		return paramsMap;
	}

	public static void main(String[] args) {
		URLModel m = HttpUrlHelper
				.parseURL("http://list.taobao.com/market/nvzhuang2011a.htm?spm=1.161859.71436.3&q=&ex_q=&isprepay=1&dtsp=1&olu=yes&cat=50095922&sort=commend&random=false&viewIndex=1&yp4p_page=0&commend=all&atype=b&s=0&style=grid&path=16-50095922&isnew=2&smc=1&smc=1&rr=1");
		System.out.println(m);

	}

}
