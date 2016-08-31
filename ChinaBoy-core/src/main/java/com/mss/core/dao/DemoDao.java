package com.mss.core.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
	
	@Autowired
	private RedisTemplate redisTemplate;
	
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
		redisTemplate.opsForHash().put("DemoPojo"+dp.hashCode(), "id",dp.getId().toString());
		redisTemplate.opsForHash().put("DemoPojo"+dp.hashCode(), "amount",dp.getAmount().toString());
		redisTemplate.opsForHash().put("DemoPojo"+dp.hashCode(), "remark",dp.getRemark());
	}
	
	/**
	 * 删除demo
	 */
	public void deleteDemo(){
		sessionTemplate.insert("demo.deleteDemo");
		redisTemplate.delete(redisTemplate.keys("DemoPojo*"));
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
