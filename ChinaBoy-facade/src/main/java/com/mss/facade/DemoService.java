package com.mss.facade;

import java.util.List;

import com.mss.pojo.DemoPojo;

/**
 * mybatis测试service
 * @author zt
 * @version 20160531
 */
public interface DemoService {

	/**
	 * 添加demo
	 * @param demoPojo
	 */
	public void addDemo(DemoPojo demoPojo);
	
	/**
	 * 修改demo
	 * @param demoPojo
	 */
	public void modifyDemo(DemoPojo demoPojo);
	
	/**
	 * 删除demo
	 * @param demoPojo
	 */
	public void removeDemo(DemoPojo demoPojo);
	
	/**
	 * 查询demo
	 * @param demoPojo
	 */
	public List<DemoPojo> queryDemo(DemoPojo demoPojo);
}
