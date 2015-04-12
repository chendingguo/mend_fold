package com.felix.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.felix.domain.Host;
import com.felix.persistence.HostMapper;
import com.felix.service.HostService;
import com.felix.utils.BeanUtil;


public class TestHost extends BaseTest {
	
	@Test
	
	public void testEvent() throws Exception {
		HostService hostService=serviceProxy.getService("hostService");
		Host host=new Host();
		
		
		hostService.insertHost(host);
		List<Host> hosts=hostService.selectHost(host);
		
		for(Host h:hosts){
			System.err.println(BeanUtil.buildString(h));
		}
	
		
		
	}
	
}
