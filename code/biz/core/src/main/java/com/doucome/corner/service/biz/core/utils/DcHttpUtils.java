package com.doucome.corner.service.biz.core.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class DcHttpUtils {

	/**
	 * ��url��ȡInputStream
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static InputStream getInputStream(String url) throws IOException {
		HttpClient client = new DefaultHttpClient() ;
		HttpGet get = new HttpGet(url) ;
		HttpResponse response = client.execute(get) ;
		int statusCode = response.getStatusLine().getStatusCode() ;
		if(statusCode != 200){
			throw new IOException("get resource error , errcode :" + statusCode) ;
		}
		HttpEntity entity = response.getEntity() ;
		//long len = entity.getContentLength() ;
		InputStream is = entity.getContent();  
		return is ;
	}
}
