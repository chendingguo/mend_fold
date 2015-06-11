package com.airsupply.adapter.solr.metadata.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * table meta data describe
 * @author arisupply
 *
 */
public class TableDesc {
	public Map<String,Object> connInfo=new HashMap<String,Object>();
	public List<ColumnVO>tableColumns=new ArrayList<ColumnVO>();
	public Map<String, Object> getConnInfo() {
		return connInfo;
	}
	public void setConnInfo(Map<String, Object> connInfo) {
		this.connInfo = connInfo;
	}
	public List<ColumnVO> getTableColumns() {
		return tableColumns;
	}
	public void setTableColumns(List<ColumnVO> tableColumns) {
		this.tableColumns = tableColumns;
	}
	
	

}
