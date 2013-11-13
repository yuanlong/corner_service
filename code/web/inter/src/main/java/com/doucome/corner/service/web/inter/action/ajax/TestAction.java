package com.doucome.corner.service.web.inter.action.ajax;

import com.doucome.corner.service.web.common.model.JsonModel;
import com.doucome.corner.service.web.inter.InterBasicAction;

public class TestAction extends InterBasicAction  {

	private JsonModel<Boolean> json = new JsonModel<Boolean>();
	
	@Override
	public String execute() throws Exception {
		
		return SUCCESS ;
	}

	public JsonModel<Boolean> getJson() {
		return json;
	}
	
	
}
