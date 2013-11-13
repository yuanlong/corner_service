package com.doucome.corner.service.biz.core.model;

import java.math.BigDecimal;


/**
 * @author ze2200
 *
 */
public class DdzTbItemModel {
	
	private String numIid;
	
	private String title;
	
	private String picUrl;
	
	private BigDecimal price;
	
	public DdzTbItemModel() {
		
	}
	
	public DdzTbItemModel(String numIid, String title, String price, String picUrl) {
		this.title = title;
		try {
			this.price = new BigDecimal(price);
		} catch(Exception e) {
			
		}
		this.picUrl = picUrl;
		int len = 4;
		int idx = picUrl.indexOf(".jpg_");
		if (idx == -1) {
			len = 5;
			idx = picUrl.indexOf(".jpeg_");
		}
		if (idx == -1) {
			len = 4;
			idx = picUrl.indexOf(".JPG_");
		}
		if (idx == -1) {
			len = 5;
			idx = picUrl.indexOf(".JPEG_");
		}
		if (idx != -1) {
			this.picUrl = picUrl.substring(0, idx + len);
		}
	}

	public String getNumIid() {
		return numIid;
	}

	public void setNumIid(String numIid) {
		this.numIid = numIid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
