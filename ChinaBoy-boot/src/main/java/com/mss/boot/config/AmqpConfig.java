package com.mss.boot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

	public static final String DIRECTQUEUE = "chinaboy.boot.directqueue";
	public static final String DIRECTEXCHANGE = "chinaboy.boot.directExchange";
	public static final String DIRECTROUTINGKEY = "chinaboy.boot.directroutingkey";
	public static final String TOPICQUEUE = "chinaboy.boot.topicqueue";
	public static final String TOPICEXCHANGE = "chinaboy.boot.topicExchange";
	public static final String TOPICROUTINGKEY = "chinaboy.boot.topicroutingkey.*";
	public static final String FANOUTQUEUE = "chinaboy.boot.fanoutqueue";
	public static final String FANOUTEXCHANGE = "chinaboy.boot.fanoutExchange";

	@Bean
	public Queue directQueue() {
		return new Queue(DIRECTQUEUE, true);
	}
	
	@Bean
	public Queue topicQueue() {
		return new Queue(TOPICQUEUE, true);
	}
	
	@Bean
	public Queue fanoutQueue() {
		return new Queue(FANOUTQUEUE, true);
	}
	
	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(DIRECTEXCHANGE);
	}
	
	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(TOPICEXCHANGE);
	}
	
	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(FANOUTEXCHANGE);
	}

	@Bean
	public Binding directBinding() {
		return BindingBuilder.bind(directQueue()).to(directExchange()).with(DIRECTROUTINGKEY);
	}
	
	@Bean
	public Binding topicBinding() {
		return BindingBuilder.bind(topicQueue()).to(topicExchange()).with(TOPICROUTINGKEY);
	}
	
	@Bean
	public Binding fanoutBinding() {
		return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
	}

}
