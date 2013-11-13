package com.doucome.corner.service.web.inter.authz.impl;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.service.biz.core.model.ChaoUserDTO;
import com.doucome.corner.service.web.inter.authz.ChaoSessionOperator;
import com.doucome.corner.service.web.inter.context.AuthzContext;
import com.doucome.corner.service.web.inter.context.AuthzContextHolder;
import com.doucome.corner.service.web.inter.context.AuthzContextModelEnums;


public class DefaultChaoSessionOperator implements ChaoSessionOperator {
 
     
    @Override 
	public boolean load(ChaoUserDTO user) {
        if (user == null || StringUtils.isEmpty(user.getUserName())) {
            return false;
        }
        AuthzContext authzContext = AuthzContextHolder.getContext();
        authzContext.clearModels();
        authzContext.setUserName(user.getUserName()) ;
        authzContext.setModel(AuthzContextModelEnums.USER_KEY, user);
        authzContext.setAuthentication(true, true);
        return true;
	}

 
    @Override
    public boolean unload() {
        AuthzContext authzContext = AuthzContextHolder.getContext();
        authzContext.setAuthentication(false, true);
        return true;
    }

	

}
