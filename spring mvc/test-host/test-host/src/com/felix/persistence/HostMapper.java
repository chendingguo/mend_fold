package com.felix.persistence;

import java.util.List;

import com.felix.domain.Host;

public interface HostMapper {

	List<Host> selectHost(Host Host);

	int insertHost(Host Host);
	
	int deleteHost(Host Host);
	
	int updateHost(Host Host);

}
