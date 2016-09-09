package com.mss.web.control;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mss.cache.RedisUtil;
import com.mss.pojo.DemoPojo;
import com.mss.util.DateUtil;

/**
 * 
 * redis测试control
 * 
 * @author zt
 * @version 20160906
 * 
 */
@RestController
@Scope("prototype")
public class RedisAction extends BaseControl{

	@Autowired
	private RedisUtil redisUtil;
	
	@RequestMapping(value="/addDemoRedis")
	public void addDemo() throws IOException{
		getCurrentUsername();
		DemoPojo demo = new DemoPojo();
		demo.setId(new BigDecimal(System.currentTimeMillis()).intValue());
		demo.setAmount(new BigDecimal(System.currentTimeMillis()));
		demo.setRemark(System.currentTimeMillis()+"");
		demo.setCreateDate(new Date());
		redisUtil.save(System.currentTimeMillis()+"", demo, 60, TimeUnit.SECONDS);
	}
	
	@RequestMapping(value="/modifyDemoRedis")
	public void modifyDemo(){
		redisUtil.delByPatten("*");
	}
	
	@RequestMapping(value="/removeDemoRedis")
	public void removeDemo(){
		redisUtil.delByPatten("*");
	}
	
	@RequestMapping(value="/queryDemoRedis")
	public void queryDemo(HttpServletResponse response) throws Exception{
		StringBuffer sb = new StringBuffer();
		Set<Serializable> set = redisUtil.keys("*");
		for(Serializable s:set){
			try{
				DemoPojo demo = redisUtil.get(s, DemoPojo.class);
				sb.append(demo.getId()+"|");
				sb.append(demo.getAmount()+"|");
				sb.append(demo.getRemark()+"|");
				sb.append(DateUtil.formatDate(demo.getCreateDate(), "yyyy/MM/dd HH:mm:ss"));
				sb.append("\n");
			}catch(Exception e){
				
			}
		}
		
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/plain");
		response.getWriter().write(sb.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}
	
}
