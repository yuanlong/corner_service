package com.doucome.corner.service.biz.dal.dataobject;

import java.util.Date;

import com.doucome.corner.service.biz.dal.model.AbstractModel;

/**
 * 专题
 * @author langben 2013-5-21
 *
 */
public class ChaoSubjectDO  extends AbstractModel{

	private Long id ;
	
	/**
	 * 专题名
	 */
	private String name ;
	
	/**
	 * 图片（","隔开）
	 */
	private String picUrls ;
	
	/**
	 * 网页
	 */
	private String webUrl ;
	
	private String status;
		
	private Date gmtCreate ;
	
	private Date gmtModified ;

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

	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	
}
