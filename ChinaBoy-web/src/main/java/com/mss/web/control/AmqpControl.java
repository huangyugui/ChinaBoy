package com.mss.web.control;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mss.pojo.DemoPojo;

/**
 * 
 * amqp测试control
 * 
 * @author zt
 * @version 20170204
 * 
 */
@RestController
@Scope("prototype")
public class AmqpControl extends BaseControl{

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@RequestMapping(value="/amqpSendDemo")
	public void sendDemo() throws IOException{
		getCurrentUsername();
		DemoPojo demo = new DemoPojo();
		demo.setId(new BigDecimal(System.currentTimeMillis()).intValue());
		demo.setAmount(new BigDecimal(System.currentTimeMillis()));
		demo.setRemark(System.currentTimeMillis()+"");
		demo.setCreateDate(new Date());
		amqpTemplate.convertAndSend(demo);
	}
	
}
