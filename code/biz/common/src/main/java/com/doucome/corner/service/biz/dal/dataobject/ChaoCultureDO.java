package com.doucome.corner.service.biz.dal.dataobject;

import java.util.Date;

import com.doucome.corner.service.biz.dal.model.AbstractModel;

/**
 * 周边文化
 * @author langben 2013-6-19
 *
 */
public class ChaoCultureDO extends AbstractModel {

	private Long id ;
	
	/**
	 * 名称
	 */
	private String name ;
	
	private String intro ;
	
	private String picUrls ;
	
	private String status ;
	
	private Date gmtModified ;
	
	private Date gmtCreate ;

	public Long getId() {
		return id;
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

	public String getIntro() {
		return intro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
}
