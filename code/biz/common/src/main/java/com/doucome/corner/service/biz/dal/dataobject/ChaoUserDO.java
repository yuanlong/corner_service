package com.doucome.corner.service.biz.dal.dataobject;

import java.util.Date;

import com.doucome.corner.service.biz.dal.model.AbstractModel;

public class ChaoUserDO extends AbstractModel {

	private Long userId ;
	
	/**
	 * 用户名（英文数字），唯一
	 */
	private String userName ;

	private String md5Password ;
	
	/**
	 * 关注活动数
	 */
	private Integer followActivityCount ;
	
	/**
	 * 收藏专题数
	 */
	private Integer favoriteSubjectCount ;
	
	/**
	 * 签到活动数
	 */
	private Integer checkinActivityCount ;
	
	/**
	 * 登陆验证
	 */
	private String authKey ;
	
	private String registerIMEI ;
	
	/**
	 * 
	 */
	private String status ;
	
	private Date gmtAuth ;
	
	private Date gmtModified ;
	
	private Date gmtCreate ;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMd5Password() {
		return md5Password;
	}

	public void setMd5Password(String md5Password) {
		this.md5Password = md5Password;
	}

	public Integer getFollowActivityCount() {
		return followActivityCount;
	}

	public void setFollowActivityCount(Integer followActivityCount) {
		this.followActivityCount = followActivityCount;
	}

	public Integer getFavoriteSubjectCount() {
		return favoriteSubjectCount;
	}

	public void setFavoriteSubjectCount(Integer favoriteSubjectCount) {
		this.favoriteSubjectCount = favoriteSubjectCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCheckinActivityCount() {
		return checkinActivityCount;
	}

	public void setCheckinActivityCount(Integer checkinActivityCount) {
		this.checkinActivityCount = checkinActivityCount;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public String getRegisterIMEI() {
		return registerIMEI;
	}

	public void setRegisterIMEI(String registerIMEI) {
		this.registerIMEI = registerIMEI;
	}

	public Date getGmtAuth() {
		return gmtAuth;
	}

	public void setGmtAuth(Date gmtAuth) {
		this.gmtAuth = gmtAuth;
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
