package com.doucome.corner.service.biz.dal.query;

import java.util.HashMap;
import java.util.Map;

import com.doucome.corner.service.biz.dal.model.AbstractModel;

public class ChaoUserFollowQuery extends AbstractModel {

	/**
	 * user
	 */
	private String userName ;
	
	/**
	 * 类型
	 */
	private String type ;
	
	/**
	 * 活动ID
	 */
	private Long activityId ;
	
	/**
	 * 专题
	 */
	private Long subjectId ;
	
	/**
	 * 商户
	 */
	private Long tenantId ;
	
	public Map<String,Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("userName", userName) ;
		map.put("type", type) ;
		map.put("activityId", activityId) ;
		map.put("subjectId", subjectId) ;
		map.put("tenantId", tenantId) ;
		return map ;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}
	
	
	
}
