package com.test.akka;

import akka.actor.UntypedActor;

public class SendActor extends UntypedActor {

	@Override
	public void preStart() throws Exception {
		System.out.println("SendActor-------------start");  
	}
	
	@Override
	public void onReceive(Object msg) throws Throwable {
		System.out.println("SendActor-------------receive message : " + msg);  
        getSender().tell("msg SendActor", getSelf());  
	}

}
