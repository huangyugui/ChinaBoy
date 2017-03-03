package com.mss.sharding.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mss.pojo.DemoPojo;

/**
 * mybatis测试dao
 * @author zt
 * @version 20160531
 */
@Repository
public class DemoDao {

	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	/**
	 * 添加demo
	 * @param demoPojo
	 */
	public synchronized void addDemo(DemoPojo demoPojo){
		DemoPojo dp = new DemoPojo();
		dp.setAmount(new BigDecimal(System.currentTimeMillis()));
		dp.setCreateDate(new Date());
		dp.setId(new Integer((int)System.currentTimeMillis()));
		dp.setRemark(System.currentTimeMillis()+"");
		sessionTemplate.insert("demo.insertDemo", dp);
	}
	
	/**
	 * 删除demo
	 */
	public void deleteDemo(){
		sessionTemplate.insert("demo.deleteDemo");
	}
	
	/**
	 * 查询demo
	 * @return
	 */
	public List<DemoPojo> selectDemoList(){
		return sessionTemplate.selectList("demo.selectDemo");
	}
	
	/**
	 * 修改demo
	 */
	public synchronized void updateDemo(DemoPojo demoPojo){
		DemoPojo dp = new DemoPojo();
		dp.setCreateDate(new Date());
		sessionTemplate.insert("demo.updateDemo", dp);
	}
}
