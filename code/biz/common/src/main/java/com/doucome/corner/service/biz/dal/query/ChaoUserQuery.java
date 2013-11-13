package com.doucome.corner.service.biz.dal.query;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.doucome.corner.service.biz.dal.model.AbstractModel;

public class ChaoUserQuery extends AbstractModel {
	
	private Long userId ;
	
	private String userName ;
	
	private Date gmtCreateStart ;
	
	private Date gmtCreateEnd ;
	
	private String status ;

	public Map<String,Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("userId", userId) ;
		map.put("userName", userName) ;
		map.put("gmtCreateStart", gmtCreateStart) ;
		map.put("gmtCreateEnd", gmtCreateEnd) ;
		map.put("status", status) ;
		return map ;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getGmtCreateStart() {
		return gmtCreateStart;
	}

	public void setGmtCreateStart(Date gmtCreateStart) {
		this.gmtCreateStart = gmtCreateStart;
	}

	public Date getGmtCreateEnd() {
		return gmtCreateEnd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setGmtCreateEnd(Date gmtCreateEnd) {
		this.gmtCreateEnd = gmtCreateEnd;
	}
	
}
