package com.doucome.corner.service.biz.dal.model;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("serial")
public class KeyValuePair extends AbstractModel {

	public KeyValuePair() {

	}

	public KeyValuePair(String key, String value) {
		this();
		this.key = key;
		this.value = value;
	}

	private String key;

	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof KeyValuePair)) {
			return false;
		}
		KeyValuePair kv = (KeyValuePair) obj;
		if (StringUtils.equals(kv.getKey(), getKey())
				&& StringUtils.equals(kv.getValue(), getValue())) {
			return true;
		}
		return false;

	}
}
