package com.doucome.corner.service.web.inter.authz.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.service.biz.core.enums.UserStatusEnums;
import com.doucome.corner.service.biz.core.model.ChaoUserDTO;
import com.doucome.corner.service.biz.core.service.chao.ChaoUserService;
import com.doucome.corner.service.web.inter.authz.ChaoAuthz;
import com.doucome.corner.service.web.inter.context.AuthzContext;
import com.doucome.corner.service.web.inter.context.AuthzContextHolder;
import com.doucome.corner.service.web.inter.context.AuthzContextModelEnums;

public class DefaultAuthzImpl implements ChaoAuthz {

	@Autowired
	private ChaoUserService chaoUserService;

	@Override
	public boolean isLogin() {
		
		AuthzContext authzContext = AuthzContextHolder.getContext();
		String authKey = authzContext.getAuthKey();
		String userName = authzContext.getUserName();

		ChaoUserDTO user = (ChaoUserDTO) authzContext.getModel(AuthzContextModelEnums.USER_KEY);
		if (user == null && StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(authKey)) {
			user = chaoUserService.getUserByUserNameAndAuthKey(userName,authKey);
			if (user != null && UserStatusEnums.toEnum(user.getStatus()) == UserStatusEnums.NORMAL) {
				authzContext.setModel(AuthzContextModelEnums.USER_KEY, user);
				authzContext.setAuthentication(true, true);
			}
		}

		return AuthzContextHolder.getContext().isAuthentication();
	}

	@Override
	public String getUserName() {
		if(!isLogin()) {
			return null ;
		}
		AuthzContext authzContext = AuthzContextHolder.getContext();
		return authzContext.getUserName() ;
	}

}
