package com.mss.boot.vo;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="分页对象")
public class PageData<T> {
	
	@ApiModelProperty(value="记录总条目数")
	/**记录总条目数*/
	private int total;
	
	@ApiModelProperty(value="记录集合")
	/**记录集合*/
	private List<T> rows = new ArrayList<T>();

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
}
