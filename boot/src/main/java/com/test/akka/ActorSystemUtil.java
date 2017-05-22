package com.test.akka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ActorSystemUtil {
	
	private static ActorSystem actorSystem = null;
    
	private static String serverName="akka";  
    private static String actorName;  
    private static String host="127.0.0.1";  
    private static int port=8888; 
    
    public static void start() {
        System.out.println("start actorSystem...");
        actorSystem = ActorSystem.create(serverName, createConfig());
    }
    
    @SuppressWarnings("rawtypes")
	public static ActorRef actorOf(Class clazz) {
        return actorSystem.actorOf(Props.create(clazz));
    }
    
    private static Config createConfig() {  
        Map<String, Object> map = new HashMap<String, Object>();  
        map.put("akka.loglevel", "ERROR");  
        map.put("akka.stdout-loglevel", "ERROR");  
  
        //开启akka远程调用  
        //map.put("akka.actor.provider", "akka.remote.RemoteActorRefProvider");  
          
        List<String> remoteTransports = new ArrayList<String>();  
        remoteTransports.add("akka.remote.netty.tcp");  
        map.put("akka.remote.enabled-transports", remoteTransports);  
          
        map.put("akka.remote.netty.tcp.hostname", host);  
        map.put("akka.remote.netty.tcp.port", port);  
          
        map.put("akka.remote.netty.tcp.maximum-frame-size", 100 * 1024 * 1024);  
          
        //forkjoinpool默认线程数 max(min(cpu线程数 * parallelism-factor, parallelism-max), 8)  
        map.put("akka.actor.default-dispatcher.fork-join-executor.parallelism-factor", "50");  
        map.put("akka.actor.default-dispatcher.fork-join-executor.parallelism-max", "50");  
            
        return ConfigFactory.parseMap(map);  
    }  
    
}
