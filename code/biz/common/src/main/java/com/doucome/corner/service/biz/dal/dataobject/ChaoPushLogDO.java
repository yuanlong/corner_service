package com.doucome.corner.service.biz.dal.dataobject;

import java.util.Date;

public class ChaoPushLogDO {

	private Long id ;
	
	private String msg ;
	
	private String pushGroup ;
	
	private Integer succCount ;
	
	private String viewType ;
	
	private String pushParam ;
	
	private Date gmtCreate ;

	public Long getId() {
		return id;
	}

	public Integer getSuccCount() {
		return succCount;
	}

	public void setSuccCount(Integer succCount) {
		this.succCount = succCount;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPushGroup() {
		return pushGroup;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public String getPushParam() {
		return pushParam;
	}

	public void setPushParam(String pushParam) {
		this.pushParam = pushParam;
	}

	public void setPushGroup(String pushGroup) {
		this.pushGroup = pushGroup;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
}
