package com.doucome.corner.service.biz.dal.query;

import java.util.HashMap;
import java.util.Map;

import com.doucome.corner.service.biz.dal.model.AbstractModel;

public class ChaoTushuoQuery extends AbstractModel {

	private String status ;
	
	private Long categoryId ;
	
	public Map<String,Object> toMap(){
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("status", status) ;
		map.put("categoryId", categoryId) ;
		return map ;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
}
