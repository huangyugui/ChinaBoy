package com.mss.mq.sender;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * 
 * topic发送工具
 * 
 * @author zt
 * @version 20160910
 *
 */
@Component
public class TopicSender {
	
	@Autowired
	private JmsTemplate topicJmsTemplate;
	
	public void sendTextMsg(final String message){
		topicJmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}
	
	public void sendObjectMsg(final Serializable obj){
		topicJmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(obj);
			}
		});
	}
	
	
}
