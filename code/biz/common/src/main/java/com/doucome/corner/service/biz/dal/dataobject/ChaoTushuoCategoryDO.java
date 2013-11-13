package com.doucome.corner.service.biz.dal.dataobject;

import java.util.Date;

import com.doucome.corner.service.biz.dal.model.AbstractModel;

@SuppressWarnings("serial")
public class ChaoTushuoCategoryDO extends AbstractModel {
	
	private Long id ;
	
	private String name ;
	
	private String intro ;
	
	private String picUrls ;
	
	private Integer tushuoCount ;
	
	private String status ;
		
	private Date gmtCreate ;
	
	private Date gmtModified ;

	public Long getId() {
		return id;
	}

	public Integer getTushuoCount() {
		return tushuoCount;
	}

	public void setTushuoCount(Integer tushuoCount) {
		this.tushuoCount = tushuoCount;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
