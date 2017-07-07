package com.mss.boot.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.mss.boot.config.AmqpConfig;
import com.mss.boot.entity.User;
import com.rabbitmq.client.Channel;

@Component
@RabbitListener(queues=AmqpConfig.DIRECTQUEUE)
public class AmqpListener {

	@RabbitHandler
	public void handle(User user, Channel channel){
		try {
			System.out.println("接收到消息:"+user);
			//channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);//手动确认消息,队列中持久化的消息会被删除
			//channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);//手动否认一条消息,消息会被无限次重新接收,直到确认消息
			//channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);//手动否认消息,消息会被无限次重新接收,直到确认消息
			//throw new RuntimeException("测试异常是否重新接收");//抛出运行异常时,消息不会被重新接收
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RabbitHandler
	public void handle(String msg, Channel channel){
		try {
			System.out.println("接收到消息:"+msg);
			System.out.println("接收到消息序号:"+channel.getNextPublishSeqNo());
			channel.basicAck(channel.getNextPublishSeqNo(), false);//手动确认消息,队列中持久化的消息会被删除
			//channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);//手动否认一条消息,消息会被无限次重新接收,直到确认消息
			//channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);//手动否认消息,消息会被无限次重新接收,直到确认消息
			//throw new RuntimeException("测试异常是否重新接收");//抛出运行异常时,消息不会被重新接收
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
