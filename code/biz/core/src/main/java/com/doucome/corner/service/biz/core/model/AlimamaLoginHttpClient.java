package com.doucome.corner.service.biz.core.model;

import org.apache.http.client.HttpClient;

/**
 * 
 * @author ze2200
 *
 */
public class AlimamaLoginHttpClient {
	
	private static final long REFRESH_INTERVAL = 20L * 60L * 1000L;
	
	private HttpClient httpClient;
	
	private long latestTimestamp;
	
	public AlimamaLoginHttpClient(HttpClient httpClient) {
		this(httpClient, System.currentTimeMillis());
	}
	
	public AlimamaLoginHttpClient(HttpClient httpClient, long latestTimestamp) {
		this.httpClient = httpClient;
		this.latestTimestamp = latestTimestamp;
	}

	public HttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public long getLatestTimestamp() {
		return latestTimestamp;
	}
	
	public void refreshTimestamp() {
		this.latestTimestamp = System.currentTimeMillis();
	}
	
	public boolean needRefresh() {
		return (System.currentTimeMillis() - latestTimestamp) > 10;//REFRESH_INTERVAL;
	}
}
