package com.livemore.persistence;

import com.livemore.model.SystemModule;

public interface SystemModuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemModule record);

    int insertSelective(SystemModule record);

    SystemModule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemModule record);

    int updateByPrimaryKey(SystemModule record);
}