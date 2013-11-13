package com.doucome.corner.service.biz.dal.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.doucome.corner.service.biz.dal.model.AbstractModel;

public class ChaoDeviceQuery extends AbstractModel  {
	
	private String imei ;
	
	private String osType ;
	
	private List<String> osTypes ;
	
	private String status ;
	
	public Map<String, Object> toMap() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("imei", imei);
		result.put("osType", osType) ;
		result.put("osTypes", osTypes) ;
		result.put("status", status) ;
		return result;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getOsType() {
		return osType;
	}

	public List<String> getOsTypes() {
		return osTypes;
	}

	public void setOsTypes(List<String> osTypes) {
		this.osTypes = osTypes;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
