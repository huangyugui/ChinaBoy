package com.mss.mq.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * 
 * topic消息监听器Slave
 * 
 * @author zt
 * @version 20160910
 *
 */
@Component
public class TopicListenerSlave implements MessageListener {
	
	@Override
	public void onMessage(Message message) {
		try {
			if(message.getClass()==org.apache.activemq.command.ActiveMQTextMessage.class){
				System.out.println("TopicListenerSlave接收到[Text类型]消息:"+((TextMessage)message).getText());
			}else{
				System.out.println("TopicListenerSlave接收到[非Text类型]消息,消息类型："+message.getClass().toString());
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	
}
