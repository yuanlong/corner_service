package com.doucome.corner.service.web.inter.action.ajax;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.service.biz.core.bo.DdzAlimamaBO;
import com.doucome.corner.service.web.common.model.JsonModel;
import com.doucome.corner.service.web.inter.InterBasicAction;

public class CommissionAction extends InterBasicAction  {
	
	private static final long serialVersionUID = 1L;

	private JsonModel<BigDecimal> json = new JsonModel<BigDecimal>();
	
	private String numIid;
	
	private String title;
	
	private BigDecimal price;
	
	@Autowired
	private DdzAlimamaBO ddzAlimamaBO;
	
	@Override
	public String execute() throws Exception {
		if (!StringUtils.isNumeric(numIid) || StringUtils.isBlank(title)) {
			json.setFail("fail");
			return SUCCESS;
		}
		BigDecimal commRate = ddzAlimamaBO.getItemCommissionRate(numIid, title);
		if (commRate != null) {
			json.setSuccess(commRate);
		} else {
			json.setFail("fail");
		}
		return SUCCESS ;
	}

	public void setNumIid(String numIid) {
		this.numIid = numIid;
	}

	public void setTitle(String title) throws UnsupportedEncodingException {
		this.title = URLDecoder.decode(title, "utf-8");
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public JsonModel<BigDecimal> getJson() {
		return json;
	}
	
	
}
