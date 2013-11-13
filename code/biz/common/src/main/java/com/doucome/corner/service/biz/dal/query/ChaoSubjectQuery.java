package com.doucome.corner.service.biz.dal.query;

import java.util.HashMap;
import java.util.Map;

import com.doucome.corner.service.biz.dal.model.AbstractModel;

public class ChaoSubjectQuery extends AbstractModel {
	
	private static final long serialVersionUID = 1L;
	
	private String status = "N";
	
	public Map<String,Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("status", status);
		return map ;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
