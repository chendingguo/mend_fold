package com.felix.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONObject;

import com.felix.domain.Host;
import com.felix.service.HostService;
import com.felix.service.ServiceProxy;
import com.opensymphony.xwork2.ActionSupport;

public class HostAction extends ActionSupport {

	private HostService hostService;
	public String hostIp;
	public String hostName;
	public String hostDesc;
    private String operationResult;
	
	public String getOperationResult() {
		return operationResult;
	}


	public void setOperationResult(String operationResult) {
		this.operationResult = operationResult;
	}

	private JSONObject result;
	public  HostAction() throws Exception {
		hostService=ServiceProxy.getInstance().getService("hostService");
	}


	public JSONObject getResult() {
		return result;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostDesc() {
		return hostDesc;
	}

	public void setHostDesc(String hostDesc) {
		this.hostDesc = hostDesc;
	}

	private static final long serialVersionUID = 1232L;

	public String insertHost() {
		Host host = new Host();
		host.setHostIp(hostIp);
		host.setHostName(hostName);
		host.setHostDesc(hostDesc);
		int r=hostService.insertHost(host);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("operationResult", r);
		
		result = JSONObject.fromObject(jsonMap);
		
		return SUCCESS;

	}
	
	
	public String deleteHost() {
		Host host = new Host();
		host.setHostIp(hostIp);
		host.setHostName(hostName);
		host.setHostDesc(hostDesc);
		int r=hostService.deleteHost(host);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("operationResult", r);
		
		result = JSONObject.fromObject(jsonMap);
		
		return SUCCESS;

	}
	
	
	public String updateHost() {
		Host host = new Host();
		host.setHostIp(hostIp);
		host.setHostName(hostName);
		host.setHostDesc(hostDesc);
		int r=hostService.updateHost(host);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("operationResult", r);
		
		result = JSONObject.fromObject(jsonMap);
		
		return SUCCESS;

	}


	

	public String getList() throws Exception {

		Host host = new Host();

		List<Host> list = hostService.selectHost(host);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", list.size());
		jsonMap.put("rows", list);
		result = JSONObject.fromObject(jsonMap);
		return SUCCESS;
	}

}
