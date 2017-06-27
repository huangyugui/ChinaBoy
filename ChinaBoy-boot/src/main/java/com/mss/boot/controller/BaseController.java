package com.mss.boot.controller;

public class BaseController {
	
	/** 默认页码 */
	protected int curPage = 0;
	
	/** 默认每页显示条目数 */
	protected int curRows = 10;
	
	/**
	 * 获取page与rows
	 * @param page
	 * @param rows
	 */
	protected void getPageRows(String page, String rows){
		if(rows!=null&&!rows.equals("")){
			curRows = Integer.parseInt(rows);
		}
		if(page!=null&&!page.equals("")){
			curPage = (Integer.parseInt(page)-1)*curRows;
		}
	}
	
	
}
