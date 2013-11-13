package com.doucome.corner.service.biz.dal.dataobject;

import java.util.Date;

import com.doucome.corner.service.biz.dal.model.AbstractModel;

/**
 * 应用推荐
 * @author langben 2013-6-4
 *
 */
public class ChaoAppRecommendDO extends AbstractModel {

	private Long id ;
	
	private String appName ;
	
	private String logoUrl ;
	
	private String intro ;
	
	private String androidStoreUrl ;
	
	private String iosStoreUrl ;
	
	private String wpStoreUrl ;
	
	private Date gmtCreate ;
	
	private Date gmtModified ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getAndroidStoreUrl() {
		return androidStoreUrl;
	}

	public void setAndroidStoreUrl(String androidStoreUrl) {
		this.androidStoreUrl = androidStoreUrl;
	}

	public String getIosStoreUrl() {
		return iosStoreUrl;
	}

	public void setIosStoreUrl(String iosStoreUrl) {
		this.iosStoreUrl = iosStoreUrl;
	}

	public String getWpStoreUrl() {
		return wpStoreUrl;
	}

	public void setWpStoreUrl(String wpStoreUrl) {
		this.wpStoreUrl = wpStoreUrl;
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
