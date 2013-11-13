package com.doucome.corner.service.biz.dal.dataobject;

import java.util.Date;

import com.doucome.corner.service.biz.dal.model.AbstractModel;

/**
 * 新闻
 * @author langben 2013-6-1
 *
 */
public class ChaoNewsDO extends AbstractModel {

	private Long id ;
	
	/**
	 * 新闻标题
	 */
	private String title ;
	
	/**
	 * 正文图片
	 */
	private String picUrls ;
	
	/**
	 * 正文
	 */
	private String content ;
	
	/**
	 * 新闻分类
	 */
	private Long categoryId ;
	
	/**
	 * 排序，一般是当前时间戳
	 */
	private Long displayOrder ;
	
	/**
	 * 评论数
	 */
	private Integer commentCount ;

	/**
	 * 
	 */
	private String status;
	
	private String isTop ;
	
	/**
	 * 来源
	 */
	private String source ;
	
	/**
	 * 发表时间
	 */
	private Date gmtPublish ;
	
	private Date gmtCreate ;
	
	private Date gmtModified ; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Long displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getGmtPublish() {
		return gmtPublish;
	}

	public void setGmtPublish(Date gmtPublish) {
		this.gmtPublish = gmtPublish;
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

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

}
