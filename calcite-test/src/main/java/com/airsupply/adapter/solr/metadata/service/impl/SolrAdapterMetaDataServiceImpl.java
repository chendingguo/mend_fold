package com.airsupply.adapter.solr.metadata.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.airsupply.adapter.solr.SolrAdapterUtil;
import com.airsupply.adapter.solr.metadata.service.AdapterMetaDataService;
import com.airsupply.adapter.solr.metadata.vo.ColumnVO;
import com.airsupply.adapter.solr.metadata.vo.TableDesc;

public class SolrAdapterMetaDataServiceImpl implements AdapterMetaDataService {
	public static SolrAdapterMetaDataServiceImpl INSTANCE = new SolrAdapterMetaDataServiceImpl();

	@Override
	public TableDesc getTableDesc(String tableName) {
		String configStr = getConfigString(tableName);
		JSONObject js = JSONObject.fromObject(configStr);
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("tableColumns", ColumnVO.class);
		TableDesc tableDesc = (TableDesc) JSONObject.toBean(js,
				TableDesc.class, classMap);
		return tableDesc;
	}

	public String getConfigString(String tableName) {
		String baseConfigFolder = System.getProperty("calcite.config.path");
		if (baseConfigFolder == null) {
			System.err.println("--|calcite.config.path is not set !");
		}
		String tableDescPath = baseConfigFolder + File.separator + tableName
				+ ".json";
		String str = SolrAdapterUtil.ReadFile(tableDescPath);
		return str;

	}

	public static void main(String[] args) {
		System.setProperty("calcite.config.path", "d:/");
		SolrAdapterMetaDataServiceImpl service = SolrAdapterMetaDataServiceImpl.INSTANCE;
		String jsonStr = service.getConfigString("DEPTS");
		JSONObject js = JSONObject.fromObject(jsonStr);
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("tableColumns", ColumnVO.class);
		TableDesc tableDesc = (TableDesc) JSONObject.toBean(js,
				TableDesc.class, classMap);
		System.out.println(tableDesc.getConnInfo());
		System.out.println(tableDesc.getTableColumns());

	}

}
