package com.livemore.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.jboss.logging.Logger;
import org.springframework.context.ApplicationContext;

public class ActionApplication extends Application {
	
	Logger logger = Logger.getLogger(getClass());

	@Override
	public Set<Object> getSingletons() {

		Set<Object> result = new HashSet<Object>();
		ApplicationContext  applicationContext=SpringContextUtil.getApplicationContext();
		String[]  names=applicationContext.getBeanDefinitionNames();
		for(String name:names){
			if(name.indexOf("Action")>0){
			result.add(applicationContext.getBean(name));
			logger.info("Bean Name:"+name);
			}
			
		}
		return result;
	}
}
