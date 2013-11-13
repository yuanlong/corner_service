package com.doucome.corner.service.biz.core.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.service.biz.core.model.DdzTbItemModel;
import com.doucome.corner.service.biz.core.utils.TaobaoUrlUtils;

/**
 * 
 * @author ZE2200
 *
 */
public enum DdzTbItemSourceEnum {
	JUHUASUAN("jhs", "ju.taobao", "GBK") {
		@Override
		public DdzTbItemModel parseFromHtml(String numIid, String html) {
			if (StringUtils.isBlank(html)) {
				return null;
			}
			String title = patternMatch(html, "<h2[\\s\\S]*?class *= *[\"\'] *name *[\"\'][\\s\\S]*?>\\s*([\\s\\S]*?)\\s*</h2>", 1);
			if (StringUtils.isEmpty(title)) {
				System.out.println(html);
				return null;
			}
			title = StringUtils.trim(title);
			
			String picUrl = patternMatch(html, "class *= *[\"\'] *normal-pic *[\"\'][\\s\\S]*?<img[\\s\\S]*?src *= *[\'\"]\\s*(http:\\S+?)\\s*[\'\"][\\s\\S]*?>", 1);
			if (StringUtils.isEmpty(picUrl)) {
				return null;
			}
			picUrl = StringUtils.trim(picUrl);
			
			String price = patternMatch(html, "class *= *[\'\"] *originPrice *[\"\'][\\s\\S]*?(\\d+(.\\d+)?)\\s*<", 1);
			price = StringUtils.trim(price);
			return new DdzTbItemModel(numIid, title, price, picUrl);
		}

		@Override
		public String getItemUrl(String numIid) {
			return TaobaoUrlUtils.getJuDetailUrl(numIid);
		}
	},
	TAOBAO("tb", "taobao", "GBK") {
		@Override
		public DdzTbItemModel parseFromHtml(String numIid, String html) {
			if (StringUtils.isBlank(html)) {
				return null;
			}
			String title = patternMatch(html, "class *= *[\"\'] *tb-detail-hd *[\"\'][\\s\\S]*?>[\\s]*?<h3[\\s\\S]{0,30}?>([\\s\\S]*?</span>)?([\\s\\S]*?)\\s*</h3>", 2);
			if (StringUtils.isEmpty(title)) {
				return null;
			}
			title = StringUtils.trim(title);
			String picUrl = patternMatch(html, "id *= *[\"\'] *J_ImgBooth *[\"\'][\\s\\S]*?src *= *[\'\"]\\s*(http:\\S+?)\\s*[\'\"][\\s\\S]*?>", 1);
			if (StringUtils.isEmpty(picUrl)) {
				return null;
			}
			picUrl = StringUtils.trim(picUrl);
			String price = patternMatch(html, "id=[\'\"] *J_StrPrice *[\"\'][\\s\\S]*?<em +class *= *[\"\']\\s*tb-rmb-num\\s*[\"\'][\\s\\S]*?>\\s*(\\d+(.\\d+)?)[\\s\\S]*?</em>", 1);
			price = StringUtils.trim(price);
			return new DdzTbItemModel(numIid, title, price, picUrl);
		}

		@Override
		public String getItemUrl(String numIid) {
			return TaobaoUrlUtils.getDetailUrl(Long.valueOf(numIid));
		}
	}, 
	CHAOSHI("cs", "chaoshi.tmall", "GBK") {
		@Override
		public DdzTbItemModel parseFromHtml(String numIid, String html) {
			if (StringUtils.isBlank(html)) {
				return null;
			}
			String title = patternMatch(html, "class *= *[\"\'] *tb-detail-hd *[\"\'][\\s\\S]*?>[\\s]*?<h3[\\s\\S]{0,30}?>\\s*([\\s\\S]*?)\\s*</h3>", 1);
			if (StringUtils.isEmpty(title)) {
				return null;
			}
			title = StringUtils.trim(title);
			
			String picUrl = patternMatch(html, "id *= *[\"\'] *J_ImgBooth *[\"\'][\\s\\S]*?src *= *[\'\"]\\s*(http:\\S+?)\\s*[\'\"][\\s\\S]*?>", 1);
			if (StringUtils.isEmpty(picUrl)) {
				return null;
			}
			picUrl = StringUtils.trim(picUrl);
			
			String price = patternMatch(html, "id *= *[\'\"] *J_StrPrice *[\"\'][\\s\\S]*?\\s*(\\d+(.\\d+)?)[\\s\\S]*?\\s*</strong>", 1);
			price = StringUtils.trim(price);
			return new DdzTbItemModel(numIid, title, price, picUrl);
		}

		@Override
		public String getItemUrl(String numIid) {
			return TaobaoUrlUtils.getChaoshiDetailUrl(numIid);
		}
	},
	TMALL("tm", "tmall", "GBK") {
		@Override
		public DdzTbItemModel parseFromHtml(String numIid, String html) {
			DdzTbItemModel item = parseFromTmallHtml(numIid, html);
//			DdzTbItemModel item = parseFromMTmallHtml(numIid, html);
//			if (item == null) {
//				item = parseFromTmallHtml(numIid, html);
//			}
			return item;
		}

		@Override
		public String getItemUrl(String numIid) {
			return TaobaoUrlUtils.getTmallDetailUrl(Long.valueOf(numIid));
		}
		
		private DdzTbItemModel parseFromMTmallHtml(String numIid, String html) {
			if (StringUtils.isBlank(html)) {
				return null;
			}
			String title = patternMatch(html, "class *= *[\"\'] *title *[\"\'][\\s\\S]*?>\\s*<h1[\\s\\S]*?>\\s*([\\s\\S]*?)\\s*</h1>", 1);
			if (StringUtils.isEmpty(title)) {
				return null;
			}
			int idx = title.indexOf("<b>");
			if (idx != -1) {
				title = title.substring(0, idx);
			}
			idx = title.indexOf("</b>");
			if (idx != -1) {
				title = title.substring(0, idx);
			}
			String picUrl = patternMatch(html, "class *= *[\"\'] *scroller *[\"\'][\\s\\S]{0,50}<img\\s+[\\s\\S]*?src *= *[\'\"]\\s*(http:\\S+?)\\s*[\'\"][\\s\\S]*?>", 1);
			if (StringUtils.isEmpty(picUrl)) {
				return null;
			}
			picUrl = StringUtils.trim(picUrl);
			title = StringUtils.trim(title);
			String price = patternMatch(html, "class *= *[\"\'] *o-price-v *[\"\'][\\s\\S]*?>[\\s\\S]*?(\\d+(.\\d+)?)[\\s\\S]*?</span>", 1);
			price = StringUtils.trim(price);
			return new DdzTbItemModel(numIid, title, price, picUrl);
		}
		
		private DdzTbItemModel parseFromTmallHtml(String numIid, String html) {
			if (StringUtils.isBlank(html)) {
				return null;
			}
			String title = patternMatch(html, "class *= *[\"\'] *tb-detail-hd *[\"\'][\\s\\S]*?>[\\s]*?<h3[\\s\\S]*?>([\\s\\S]*?)</h3>", 1);
			if (StringUtils.isEmpty(title)) {
				System.out.println(html);
				return null;
			}
			title = StringUtils.trim(title);
			String picUrl = patternMatch(html, "id *= *[\"\'] *J_ImgBooth *[\"\'][\\s\\S]{0,20}?src *= *[\'\"]\\s*(http:\\S+?)\\s*[\'\"][\\s\\S]*?>", 1);
			if (StringUtils.isEmpty(picUrl)) {
				return null;
			}
			picUrl = StringUtils.trim(picUrl);
			String price = patternMatch(html, "class *= *[\"\'] *J_originalPrice *[\"\'][\\s\\S]*?>\\s*(\\d+(.\\d+)?)\\s?</strong>", 1);
			price = StringUtils.trim(price);
			return new DdzTbItemModel(numIid, title, price, picUrl);
		}
	},
	UNKNOWN("", "", "") {
		@Override
		public DdzTbItemModel parseFromHtml(String html, String numIid) {
			return null;
		}

		@Override
		public String getItemUrl(String numIid) {
			return null;
		}
	};
	
	private DdzTbItemSourceEnum(String uid, String keyword, String pageCharset){
		this.uid = uid;
		this.keyword = keyword;
		this.pageCharset = pageCharset;
	}
	
	private String uid;
	
	private String keyword;
	
	private String pageCharset;
	
	public String getUid() {
		return this.uid;
	}
	
	public String getKeyword(){
		return this.keyword;
	}
	
	public String getPageCharset() {
		return pageCharset;
	}
	
	/**
	 * 
	 * @param numIid
	 * @param html
	 * @return
	 */
	public abstract DdzTbItemModel parseFromHtml(String numIid, String html);
	
	/**
	 * 
	 * @param numIid
	 * @return
	 */
	public abstract String getItemUrl(String numIid);
	

	
	public String patternMatch(String html, String regStr, int groupIdx) {
		Matcher matcher = Pattern.compile(regStr).matcher(html);
		if (matcher.find()) {
			return matcher.group(groupIdx);
		} else {
			return null;
		}
	}
	
	public static DdzTbItemSourceEnum toEnum(String host) {
		if (StringUtils.isEmpty(host)) {
			return UNKNOWN;
		}
		for (DdzTbItemSourceEnum temp: values()) {
			if (host.indexOf(temp.getKeyword()) != -1 || host.equalsIgnoreCase(temp.getUid())) {
				return temp;
			}
		}
		return UNKNOWN;
	}
}
