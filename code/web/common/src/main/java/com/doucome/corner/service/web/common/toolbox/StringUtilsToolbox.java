package com.doucome.corner.service.web.common.toolbox;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import com.doucome.corner.service.biz.dal.model.KeyValuePair;

public class StringUtilsToolbox extends StringUtils {

    public static String urlEncode(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static String escapeHtml(String str) {
        return StringEscapeUtils.escapeHtml(str);
    }

    public static String escapeJS(String str) {
        return StringEscapeUtils.escapeJavaScript(str);
    }

    public static String escapeHiddenInputs(String str) {
        if (StringUtils.isNotBlank(str)) {
            String[] prams = StringUtils.split(str, '&');
            if (prams.length > 0) {
                StringBuilder builder = new StringBuilder();
                for (String pram : prams) {
                    String[] pairs = StringUtils.split(pram, '=');
                    if (pairs.length > 1) {
                        builder.append(escapeHiddenInput(pairs[0], pairs[1]));
                    }
                }
                return builder.toString();
            }
        }
        return StringUtils.EMPTY;
    }

    public static String escapeHiddenInput(String name, String value) {
        if (StringUtils.isNotBlank(name)) {
            return "<input type='hidden' name='" + name + "' value='" + value + "' />";
        }
        return StringUtils.EMPTY;
    }

    public static String replaceIgnorecase(String text, String searchString, String replacement) {
        String lowcase = StringUtils.lowerCase(searchString);
        String uppercase = StringUtils.upperCase(searchString);
        text = StringUtils.replace(text, lowcase, replacement);
        text = StringUtils.replace(text, uppercase, replacement);
        return text;
    }

    public static String concat(String... strArray) {
        String rt = "";
        for (String s : strArray) {
            rt += s;
        }
        return rt;
    }

    /**
     * 
     * 
     * @param str
     * @param end
     * @param strAfter
     * @return
     */
    public static String substring(String str, int length, String strAfter) {
        int strLen = StringUtils.length(str);
        if (length >= strLen) {
            return str;
        }

        String s = StringUtils.substring(str, 0, length);
        return s + strAfter;
    }

    /**
     * @param arr1
     * @param arr2
     * @return
     */
    public static List<KeyValuePair> combineQueryArray(List<String> oriQuery, List<String> overlapList) {
    	
    	Map<String,KeyValuePair> overlapMap = new HashMap<String,KeyValuePair>();
        
    	List<KeyValuePair> oriKVList = new ArrayList<KeyValuePair> () ;
        
        if (CollectionUtils.isNotEmpty(oriQuery)) {
            for (String keyval : oriQuery) {
            	KeyValuePair kv = parseStr2KV(keyval) ;
                if(kv != null){
                	oriKVList.add(kv) ;
                }
            }
        }
        
        if (CollectionUtils.isNotEmpty(overlapList)) {
            for (String keyval : overlapList) {
                KeyValuePair kv = parseStr2KV(keyval) ;
                if(kv != null){
                	overlapMap.put(kv.getKey(), kv) ;
                }
            }
        }

        if(MapUtils.isNotEmpty(overlapMap)) {
        	Set<Entry<String,KeyValuePair>> entrySet = overlapMap.entrySet() ;
        	for(Entry<String,KeyValuePair> entry : entrySet){
        		String key = entry.getKey() ;
        		KeyValuePair value = entry.getValue() ;
        		boolean exists = false ;
        		for(KeyValuePair oriKV : oriKVList) {
        			if(StringUtils.equals(oriKV.getKey() , key)){
        				oriKV.setValue(value.getValue()) ;
        				exists = true ;
        				break ;
        			}
        		}
        		if(!exists) {
        			oriKVList.add(value) ;
        		}
        	}
        }
        
        return oriKVList ;
    }
    
    /**
     *  
     * @param str
     * @return
     */
    private static KeyValuePair parseStr2KV(String str){
    	String[] a = StringUtils.split(str, ":");
    	if(a == null || a.length == 0){
    		return null ;
    	} else {
    		 if (a.length == 1) {
             	return new KeyValuePair(a[0], "");
             } else {
             	return new KeyValuePair(a[0] , a[1]) ;
             }
    	}
    }
    
    public String trimTo(String str) {
    	return str == null? str: str.trim();
    }
    
}
