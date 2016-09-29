package com.mss.mq.listener;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;
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
public class TopicListenerSlave implements SessionAwareMessageListener<TextMessage> {
	
	private static int count=0;
	
	@Override
	public void onMessage(TextMessage message, Session session) throws JMSException {
		try {
			System.out.println("TopicListenerSlave接收到[Text类型]消息:"+message.getText()+",通知次数:"+count++);
			//session.recover();//该消息会被重新接收,默认重新接收6次
			//session.commit();//事务提交,消息确认
			//session.rollback();//事务回滚,消息被重新接收,默认重新接收6次
			//throw new RuntimeException("测试异常是否回滚");//事务回滚,消息被重新接收,默认重新接收6次
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
