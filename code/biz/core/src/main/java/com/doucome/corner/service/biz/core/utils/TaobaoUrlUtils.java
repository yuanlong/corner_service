package com.doucome.corner.service.biz.core.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.service.biz.core.model.URLModel;


public class TaobaoUrlUtils {
	
	private static final String TAOBAO_DETAIL_URL_PREFIX = "http://item.taobao.com/item.htm?id=" ;
	
	private static final String TMALL_DETAIL_URL_PREFIX = "http://detail.tmall.com/item.htm?id=" ;
	
	private static final String TAOBAO_MIAOSHA_HOST = "miao.item.taobao.com";
	
	private static final String M_TMALL_ITEM_URL_FMT = "http://a.m.tmall.com/i%s.htm";
	
	private static final String CHAOSHI_ITEM_URL = "http://chaoshi.detail.tmall.com/item.htm?id=";
	
	private static final String JU_ITEM_URL = "http://ju.taobao.com/tg/home.htm?item_id=";
	
	/**
	 * 
	 */
	public static final String JU_DOMAIN = "ju.taobao.com" ;
	
	public static String parseItemId(String itemUrl) {
		URLModel model = HttpUrlHelper.parseURL(itemUrl);
		if (model == null) {
			return null;
		}
		return parseItemId(model);
	}
	
	public static String parseShopId(String shopUrl) {
		URLModel model = HttpUrlHelper.parseURL(shopUrl);
		if (model == null) {
			return null;
		}
		return parseShopId(model);
	}
	
	/**
	 * ��ȡ�ƶ����Ա���Ʒid.
	 * @param mItemUrl
	 * @return
	 */
	public static String parseMobileItemId(String mItemUrl) {
		if (StringUtils.isEmpty(mItemUrl) || mItemUrl.indexOf("taobao.com") == -1) {
			return "";
		}
		try {
			URL url = new URL(mItemUrl);
			String path = url.getPath();
			int index = path.lastIndexOf("/");
			if (index > 0) {
				path = path.substring(index + 1);
			}
			index = path.lastIndexOf(".");
			if (index > 0) {
				path = path.substring(0, index);
			}
			String itemId = null;
			for (int i = 0; i < path.length(); i++) {
				itemId = path.substring(i);
				try {
					Long.valueOf(itemId);
					return itemId;
				} catch (NumberFormatException e) {
					
				}
			}
			return "";
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * �Ƿ��Ǿۻ������ID����IDһ����1000��ͷ
	 * @param id
	 * @return
	 */
	public static boolean isJuGroupId(String id) {
		if(StringUtils.length(id) >= 14 && StringUtils.startsWith(id, "1000000")) {
			return true ;
		}
		return false ;
	}

	public static String parseItemId(URLModel model){
		if(model == null){
			return null ;
		}
		Map<String,String> params = model.getParams() ;
		if(params == null){
			return null ;
		}
		String itemId = params.get("item_id") ;
		
		if(StringUtils.isBlank(itemId)){
			itemId = params.get("itemid") ;
		}
		if(StringUtils.isBlank(itemId)){
			itemId = params.get("itemId") ;
		}
		if(StringUtils.isBlank(itemId)){
			itemId = params.get("id") ;
		}
		if(StringUtils.isBlank(itemId)){
			itemId = params.get("default_item_id") ;
		}
		if(StringUtils.isBlank(itemId)){
			itemId = params.get("mallstitemid") ;
		}
		if(StringUtils.isBlank(itemId)){
			itemId = params.get("mallstItemId") ;
		}
		if (model.getHost().indexOf(TAOBAO_MIAOSHA_HOST) != -1) {
			String path = model.getPath();
			int idx1 = path.lastIndexOf("/"), idx2 = path.indexOf(".");
			if (idx1 != -1 && idx2 != -1) {
				itemId = path.substring(idx1 + 1, idx2);
			}
		}
		if(!StringUtils.isNumeric(itemId)){
			return null ;
		}
		return itemId ;
	}
	
	public static String parseShopId(URLModel model){
		if(model == null){
			return null ;
		}
		Map<String,String> params = model.getParams() ;
		if(params == null){
			return null ;
		}
		String shopId = params.get("shop_id") ;
		
		if(StringUtils.isNotBlank(shopId)){
			return shopId;
		}
		shopId = params.get("user_number_id") ;
		return shopId;
	}
	
	public static String getDetailUrl(Long itemId) {
		return TAOBAO_DETAIL_URL_PREFIX + itemId ;
	}
	
	public static String getTmallDetailUrl(Long itemId) {
		return TMALL_DETAIL_URL_PREFIX + itemId ;
	}
	
	public static String getMTmallItemUrl(String numIid) {
		return String.format(M_TMALL_ITEM_URL_FMT, numIid);
	}
	
	public static String getChaoshiDetailUrl(String itemId) {
		return CHAOSHI_ITEM_URL + itemId ;
	}
	
	public static String getJuDetailUrl(String itemId) {
		return JU_ITEM_URL + itemId ;
	}
	
	public static boolean isTaobaoUrl(String url) {
		return url != null && (url.indexOf("taobao.com") != -1 || url.indexOf("tmall.com") != -1);
	}
	
	public static void main(String[] args) throws MalformedURLException {
		System.out.println(parseMobileItemId("http://a.m.taobao.com/a/i15348329368.htm?ttid=228200@taobao_android_3.6.2"));
	}
}
