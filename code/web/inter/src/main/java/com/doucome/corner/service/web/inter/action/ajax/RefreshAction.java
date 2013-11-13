package com.doucome.corner.service.web.inter.action.ajax;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.service.biz.core.bo.DdzAlimamaBO;
import com.doucome.corner.service.web.common.model.JsonModel;
import com.doucome.corner.service.web.inter.InterBasicAction;

public class RefreshAction extends InterBasicAction  {
	
	private static final long serialVersionUID = 1L;

	private JsonModel<BigDecimal> json = new JsonModel<BigDecimal>();
	
	@Autowired
	private DdzAlimamaBO ddzAlimamaBO;
	
	private String destUrl;
	
	@Override
	public String execute() throws Exception {
		//destUrl = StringUtils.isNotEmpty(destUrl)? destUrl: "http://u.alimama.com/membersvc/index.htm"
		ddzAlimamaBO.refreshLoginStatus("http://u.alimama.com/union/myunion/myOverview.htm");
		return SUCCESS;
	}

	public void setDesturl(String destUrl) {
		this.destUrl = destUrl;
	}

	public JsonModel<BigDecimal> getJson() {
		return json;
	}
	
	
}
