package redis;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

public class TestSendinel {

	public static void main(String[] args) {
		
		 JedisPoolConfig poolConfig = new JedisPoolConfig();
		 //连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
		 poolConfig.setBlockWhenExhausted(true);
		 
		 Set sentinels = new HashSet();
	     /*sentinels.add(new HostAndPort("192.168.110.110", 26379).toString());
	     sentinels.add(new HostAndPort("192.168.110.111", 26379).toString());
	     sentinels.add(new HostAndPort("192.168.110.112", 26379).toString());*/
		 sentinels.add(new HostAndPort("192.168.65.56", 26379).toString());
	     sentinels.add(new HostAndPort("192.168.65.56", 26378).toString());
	     sentinels.add(new HostAndPort("192.168.65.56", 26380).toString());
	     JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels, "YNETRedis192.168.65.56");
	     //JedisSentinelPool pool = new JedisSentinelPool();
	     System.out.println("Current master: " + sentinelPool.getCurrentHostMaster().toString());
	     /*Jedis master = sentinelPool.getResource();
	     master.flushAll();
	     master.set("test","tao1");
	     sentinelPool.returnResource(master);
	     //master.flushAll();
*/	     Jedis master2 = sentinelPool.getResource();
	     String value = master2.get("test");
         System.out.println("test: " + value);
         master2.close();
         sentinelPool.destroy();
	}

}
