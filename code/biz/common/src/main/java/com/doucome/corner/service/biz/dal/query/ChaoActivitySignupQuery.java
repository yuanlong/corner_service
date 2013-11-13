package com.doucome.corner.service.biz.dal.query;

import java.util.HashMap;
import java.util.Map;

public class ChaoActivitySignupQuery {

	private Long id;
	
	private Long activityId;
	
	public Map<String,Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("id", id) ;
		map.put("activityId", activityId) ;
		return map ;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	
	
}
