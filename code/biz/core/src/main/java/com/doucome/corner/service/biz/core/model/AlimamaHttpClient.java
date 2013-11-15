package com.doucome.corner.service.biz.core.model;

import org.apache.http.client.HttpClient;

/**
 * 
 * @author ze2200
 *
 */
public class AlimamaHttpClient {
	
	private static final long REFRESH_INTERVAL = 5L * 60L * 1000L;
	
	private HttpClient httpClient;
	
	private long latestTimestamp;
	
	private int loginInfoIdx;
	
	public AlimamaHttpClient(HttpClient httpClient, int idx) {
		this.httpClient = httpClient;
		loginInfoIdx = idx;
		this.latestTimestamp = System.currentTimeMillis();
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
	
	public int getLoginInfoIdx() {
		return loginInfoIdx;
	}

	public void setLoginInfoIdx(int loginInfoIdx) {
		this.loginInfoIdx = loginInfoIdx;
	}

	public void refreshTimestamp() {
		this.latestTimestamp = System.currentTimeMillis();
	}
	
	public boolean needRefresh() {
		return (System.currentTimeMillis() - latestTimestamp) > REFRESH_INTERVAL;
	}
}
