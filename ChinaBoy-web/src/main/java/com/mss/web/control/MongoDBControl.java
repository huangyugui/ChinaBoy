package com.mss.web.control;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mss.pojo.DemoPojo;
import com.mss.util.DateUtil;

/**
 * MongoDB测试control
 * @author zt
 * @version 201601010
 */
@RestController
@Scope("prototype")
public class MongoDBControl extends BaseControl{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@RequestMapping(value="/mongodbAdd")
	public void add(){
		DemoPojo demo = new DemoPojo();
		demo.setId(new BigDecimal(System.currentTimeMillis()).intValue());
		demo.setAmount(new BigDecimal(System.currentTimeMillis()));
		demo.setRemark(System.currentTimeMillis()+"");
		demo.setCreateDate(new Date());
		mongoTemplate.insert(demo);
	}
	
	@RequestMapping(value="/mongodbModify")
	public void modify(){
		Query query = new Query();
		Update update = new Update();
		update.set("remark", "update");
		mongoTemplate.updateMulti(query, update, DemoPojo.class);
	}
	
	@RequestMapping(value="/mongodbRemove")
	public void remove(){
		mongoTemplate.dropCollection(DemoPojo.class);
	}
	
	@RequestMapping(value="/mongodbQuery")
	public void query(HttpServletResponse response) throws Exception{
		StringBuffer sb = new StringBuffer();
		List<DemoPojo> list = mongoTemplate.findAll(DemoPojo.class);
		for(DemoPojo demo:list){
			try{
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
