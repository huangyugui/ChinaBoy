package com.mss.web.control;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mss.mq.sender.QueueSender;
import com.mss.mq.sender.TopicSender;
import com.mss.pojo.DemoPojo;

/**
 * 
 * activemq测试control
 * 
 * @author zt
 * @version 20160910
 * 
 */
@RestController
@Scope("prototype")
public class ActivemqControl extends BaseControl{
	
	@Autowired
	private QueueSender queueSender;
	
	@Autowired
	private TopicSender topicSender;
	
	@RequestMapping(value="/activemqSendDemo")
	public void sendDemo() throws IOException{
		getCurrentUsername();
		DemoPojo demo = new DemoPojo();
		demo.setId(new BigDecimal(System.currentTimeMillis()).intValue());
		demo.setAmount(new BigDecimal(System.currentTimeMillis()));
		demo.setRemark(System.currentTimeMillis()+"");
		demo.setCreateDate(new Date());
		queueSender.sendTextMsg(demo.toString());
		topicSender.sendTextMsg(demo.toString());
	}
	
}
