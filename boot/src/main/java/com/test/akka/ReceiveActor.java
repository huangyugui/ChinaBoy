package com.test.akka;

import akka.actor.UntypedActor;

public class ReceiveActor extends UntypedActor {

	@Override
	public void preStart() throws Exception {
		System.out.println("ReceiveActor-------------start");  
	}
	
	@Override
	public void onReceive(Object msg) throws Throwable {
		System.out.println("ReceiveActor-------------receive message : " + msg);
	}

}
