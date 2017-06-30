package com.mss.boot.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="hello")
public class AmqpListener {

	@RabbitHandler
	public void handle(String msg){
		System.out.println("Receiver  : " + msg);
	}
}
