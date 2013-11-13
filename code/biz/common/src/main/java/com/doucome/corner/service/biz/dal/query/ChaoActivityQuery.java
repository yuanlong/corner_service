package com.doucome.corner.service.biz.dal.query;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.doucome.corner.service.biz.dal.model.AbstractModel;

public class ChaoActivityQuery extends AbstractModel  {
	
	private Long id;
	
	private List<Long> ids;
	
	private String keyword;
	
	private Long subjectId;
	
	private String status;
	
	private Date gmtActivityStart;
	
	private Date gmtActivityEnd;
	
	public Map<String, Object> toMap() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", id);
		result.put("ids", ids) ;
		result.put("keyword", keyword);
		result.put("subjectId", subjectId);
		result.put("status", status);
		result.put("gmtActivityStart", gmtActivityStart);
		result.put("gmtActivityEnd", gmtActivityEnd);
		return result;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getGmtActivityStart() {
		return gmtActivityStart;
	}

	public void setGmtActivityStart(Date gmtActivityStart) {
		this.gmtActivityStart = gmtActivityStart;
	}

	public Date getGmtActivityEnd() {
		return gmtActivityEnd;
	}

	public void setGmtActivityEnd(Date gmtActivityEnd) {
		this.gmtActivityEnd = gmtActivityEnd;
	}
}
