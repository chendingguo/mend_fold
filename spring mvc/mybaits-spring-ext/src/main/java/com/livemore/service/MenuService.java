package com.livemore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livemore.model.SystemModule;
import com.livemore.persistence.SystemModuleMapper;
@Service
public class MenuService {
	@Autowired
	private SystemModuleMapper systemModuleMapper;

	int deleteByPrimaryKey(Long id) {
		return 0;
	}

	int insert(SystemModule record) {
		return 0;
	}

	int insertSelective(SystemModule record) {
		return 0;
	}

	public SystemModule selectByPrimaryKey(Long id) {
		return systemModuleMapper.selectByPrimaryKey(id);
	}

	int updateByPrimaryKeySelective(SystemModule record) {
		return 0;
	}

	int updateByPrimaryKey(SystemModule record) {
		return 0;
	}

}
