package com.livemore.model;

import mybatis.page.Page;

public class BaseModel {
	public String sortColumn;
	public Page page;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}
	
	
	

}
