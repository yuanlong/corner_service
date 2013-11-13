package com.doucome.corner.service.biz.dal.query;

import java.util.HashMap;
import java.util.Map;

public class ChaoUserCommentQuery {

	public Map<String,Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("toCommentId", toCommentId) ;
		map.put("toUserName", toUserName) ;
		map.put("userName", userName) ;
		map.put("activityId", activityId) ;
		map.put("newsId", newsId) ;
		map.put("type", type) ;
		map.put("status", status) ;
		return map ;
	}
	
	private Long toCommentId ;
	
	private String toUserName ;
	
	private String userName ;
	
	private Long activityId ;
	
	private Long newsId ;
	
	private String type ;
	
	private String status ;

	public Long getToCommentId() {
		return toCommentId;
	}

	public void setToCommentId(Long toCommentId) {
		this.toCommentId = toCommentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
