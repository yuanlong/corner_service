package com.doucome.corner.service.web.common.toolbox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.CollectionUtils;

public class CollectionUtilsToolbox extends CollectionUtils {

	public static boolean contains(Collection collection , Object element) {
		if(collection == null){
			return false ;
		}
		return collection.contains(element) ;
	}

	public static Object[] add(String[] arr , String element){
		return ArrayUtils.add(arr , element) ;
 	}
	
	public static List<String> add(List<String> list , String element){
		if(list == null){
			return null ;
		}
		list.add(element) ;
		return list ;
	}
	
	public static boolean isEmpty(Collection collection) {
		return (collection == null || collection.isEmpty());
	}
	
	public static List<Object> wrapperToList(Object o) {
		List<Object> list = new ArrayList<Object>() ;
		list.add(o) ;
		return list ;
	}
}
