package com.felix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felix.domain.Host;
import com.felix.persistence.HostMapper;

@Service
public class HostService {
	@Autowired 
	private HostMapper hostMapper;
	
	public List<Host> selectHost(Host host){
		return hostMapper.selectHost(host);
		
	}
	
	
	public int insertHost(Host host){
		return hostMapper.insertHost(host);
		
	}
	
	
	public int deleteHost(Host host){
		return hostMapper.deleteHost(host);
		
	}
	
	
	public int updateHost(Host host){
		return hostMapper.updateHost(host);
		
	}


}
