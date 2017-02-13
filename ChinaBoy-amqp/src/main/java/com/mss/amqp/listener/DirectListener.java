package com.mss.amqp.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 
 * 消息监听器,自动确认消息
 * 
 * @author zt
 * @version 20170204
 *
 */
@Component
public class DirectListener implements MessageListener{
	
	private static int count=1;
	
	/**
	 * 自动确认消息,抛出运行异常时,消息会被无限次重新接收
	 */
	@Override
	public void onMessage(Message message) {
		//try {
			System.out.println("DirectListener接收到消息:"+new String(message.getBody())+",通知次数:"+count++);
			//throw new RuntimeException("测试异常是否重新接收");//抛出运行异常时,消息会被无限次重新接收
		/*} catch (Exception e) {
			e.printStackTrace();
		}*/
		
	}
	

}
