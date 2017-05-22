package com.test.akka;

import akka.actor.ActorRef;

public class TestAkka {

	public static void main(String[] args) {
		ActorSystemUtil.start();  
        ActorRef receive = ActorSystemUtil.actorOf(ReceiveActor.class);  
        ActorRef send = ActorSystemUtil.actorOf(SendActor.class);  
        send.tell("Hello! I am SendActor!", receive);  
	}

}
