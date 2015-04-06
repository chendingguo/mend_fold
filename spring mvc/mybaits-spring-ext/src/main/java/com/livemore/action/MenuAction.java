/*
 * Copyright (c) 2001-2014 Bidlink(Beijing) E-Biz Tech Co.,Ltd.
 * All rights reserved.
 * 必联（北京）电子商务科技有限公司 版权所有
 * <p>LoginAction.java</p>
 */
package com.livemore.action;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.livemore.model.SystemModule;
import com.livemore.service.MenuService;





@Controller
@Path(value = "menu")
public class MenuAction {
	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private MenuService menuService;
		
	/**
	 * 删除菜单
	 * 
	 * @param request
	 * @return
	 */
	@GET
	@Path("getMenu")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getMenu(@Context HttpServletRequest request) {
		
		SystemModule module=menuService.selectByPrimaryKey(1L);
	
	   logger.info("---------------------");
		return Response.ok(module).build();
	}

}
