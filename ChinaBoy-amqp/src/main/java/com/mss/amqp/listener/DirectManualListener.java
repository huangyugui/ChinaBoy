package com.mss.amqp.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

/**
 * 
 * 消息监听器,手动确认消息
 * 
 * @author zt
 * @version 20170204
 *
 */
@Component
public class DirectManualListener implements ChannelAwareMessageListener {

	private static int count=1;
	
	/**
	 * 手动确认消息
	 * channel.basicAck确认消息
	 * channel.basicReject否认一条消息,消息会重新接收
	 * channel.basicNack否认一条或多条消息,消息会重新接收
	 * 抛异常时消息不会重发,只有否认消息才会重发
	 * 若未调用basicAck确认则消息会产生堆积(影响后续消息的接收),那么当消费者下再一次连接rabbitmq时消息会重发给消费者
	 */
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		//try {
			System.out.println("DirectManualListener接收到消息:"+new String(message.getBody())+",通知次数:"+count++);
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);//手动确认消息,队列中持久化的消息会被删除
			//channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);//手动否认一条消息,消息会被无限次重新接收,直到确认消息
			//channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);//手动否认消息,消息会被无限次重新接收,直到确认消息
			//throw new RuntimeException("测试异常是否重新接收");//抛出运行异常时,消息不会被重新接收
		/*} catch (Exception e) {
			e.printStackTrace();
		}*/
		
	}

}
