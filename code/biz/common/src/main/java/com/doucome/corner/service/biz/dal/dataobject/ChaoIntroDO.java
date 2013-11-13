package com.doucome.corner.service.biz.dal.dataobject;

import java.util.Date;

import com.doucome.corner.service.biz.dal.model.AbstractModel;

/**
 * 海宁，潮博会介绍
 * @author langben 2013-6-19
 *
 */
public class ChaoIntroDO extends AbstractModel {

	private Long id ;
	
	/**
	 * 海宁地图
	 */
	private String hnMapPicUrls ;
	
	/**
	 * 海宁简介
	 */
	private String hnIntro ;
	
	/**
	 * 四大产业
	 */
	private String hnIndustrial ;
	
	/**
	 * 潮博会简介
	 */
	private String chaoExpoIntro ;
	
	private Date gmtCreate ;
	
	private Date gmtModified ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHnMapPicUrls() {
		return hnMapPicUrls;
	}

	public void setHnMapPicUrls(String hnMapPicUrls) {
		this.hnMapPicUrls = hnMapPicUrls;
	}

	public String getHnIntro() {
		return hnIntro;
	}

	public void setHnIntro(String hnIntro) {
		this.hnIntro = hnIntro;
	}

	public String getChaoExpoIntro() {
		return chaoExpoIntro;
	}

	public void setChaoExpoIntro(String chaoExpoIntro) {
		this.chaoExpoIntro = chaoExpoIntro;
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

	public String getHnIndustrial() {
		return hnIndustrial;
	}

	public void setHnIndustrial(String hnIndustrial) {
		this.hnIndustrial = hnIndustrial;
	}
	
}
