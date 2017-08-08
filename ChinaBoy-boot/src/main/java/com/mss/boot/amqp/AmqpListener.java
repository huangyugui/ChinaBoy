package com.mss.boot.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.mss.boot.config.AmqpConfig;
import com.mss.boot.entity.User;
import com.rabbitmq.client.Channel;

@Component
@RabbitListener(queues=AmqpConfig.DIRECTQUEUE)
public class AmqpListener {

	/**
	 * 接收User对象消息
	 * @param user
	 * @param deliveryTag
	 * @param channel
	 */
	@RabbitHandler
	public void handle(User user, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel){
		try {
			System.out.println("接收到消息:"+user);
			channel.basicAck(deliveryTag, false);//deliveryTag为消息序列号,手动确认消息,队列中持久化的消息会被删除
			//channel.basicReject(deliveryTag, true);//手动否认一条消息,消息会被无限次重新接收,直到确认消息
			//channel.basicNack(deliveryTag, false, true);//手动否认多条小于deliveryTag序列消息,消息会被无限次重新接收,直到确认消息
			//throw new RuntimeException("测试异常是否重新接收");//抛出运行异常时,消息不会被重新接收
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 接收String消息
	 * @param msg
	 * @param deliveryTag
	 * @param channel
	 */
	@RabbitHandler
	public void handle(String msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel){
		try {
			System.out.println("接收到消息:"+msg);
			channel.basicAck(deliveryTag, false);//deliveryTag为消息序列号,手动确认消息,队列中持久化的消息会被删除
			//channel.basicReject(deliveryTag, true);//手动否认一条消息,消息会被无限次重新接收,直到确认消息
			//channel.basicNack(deliveryTag, false, true);//手动否认多条小于deliveryTag序列消息,消息会被无限次重新接收,直到确认消息
			//throw new RuntimeException("测试异常是否重新接收");//抛出运行异常时,消息不会被重新接收
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
