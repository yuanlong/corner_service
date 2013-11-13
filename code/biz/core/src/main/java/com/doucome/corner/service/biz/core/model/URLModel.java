package com.doucome.corner.service.biz.core.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.service.biz.core.utils.HttpUrlHelper;
import com.doucome.corner.service.biz.dal.model.AbstractModel;


public class URLModel extends AbstractModel {
	
	private static final Log log = LogFactory.getLog(URLModel.class) ;

	private String host;

	private String protocol;

	private int port;

	private String ref;
	
	private String path ;

	private Map<String, String> params;

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Map<String, String> getParams() {
		if(params == null){
			params = new HashMap<String,String>() ;
		}
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder() ;
		sb.append(protocol).append("://").append(host) ;
		if(port != -1){
			sb.append(":").append(port) ;
		}
		if(StringUtils.isNotBlank(path)){
			sb.append(path) ;
		}
		if(MapUtils.isNotEmpty(params)){
			sb.append("?") ;
			Set<Map.Entry<String, String>> entrySet = params.entrySet() ;
			for( Iterator<Map.Entry<String, String>> i = entrySet.iterator() ;i.hasNext();){
				Map.Entry<String, String> entry = i.next() ;
				try {
					sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(),"UTF-8")) ;
				} catch (UnsupportedEncodingException e) {
					log.error(e.getMessage() , e) ;
				}
				if(i.hasNext()){
					sb.append("&") ;
				}
			}
		}
		return sb.toString() ;
	}
	
	public static void main(String[] args) {
		
		String ori = "http://s.click.taobao.com/t?e=zGU34CA7K%2BPkqB07S4%2FK0CFcRfH0G7DbPkiN9MJoi0R4PBP9uXYKaTu1FaKuHgmKp%2BvStAL4kXn2Z9bd5wlyXu83HWiSeSvhQRZacERPbFuNLcZFDDagjwH48qK1aGoJs9p2xodvd9Tc6OQ%2FmhxOMS1xDulKmeIXR2e%2FLg%3D%3D&pid=mm_30329788_0_0&unid=Anull&spm=2014.12625882.1.0" ;
		
		URLModel u = HttpUrlHelper.parseURL(ori) ;
		String dec = u.toString() ;
		System.out.println(dec.equals(ori));
		System.out.println(dec);
	}
}
