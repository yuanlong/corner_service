package com.doucome.corner.service.web.inter.authz;

import com.doucome.corner.service.biz.core.model.ChaoUserDTO;

/**
 * 
 * @author langben 2013-5-21
 * 
 */
public interface ChaoSessionOperator {
 
	 boolean load(ChaoUserDTO user) ;
	 
	 boolean unload() ; 
}
