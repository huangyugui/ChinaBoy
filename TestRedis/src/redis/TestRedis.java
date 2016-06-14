package redis;

import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestRedis {

	public static void main(String[] args) {
		//Jedis jedis = new Jedis("192.168.110.110",6379);//ip,port
		Jedis jedis = new Jedis("192.168.65.56",6379);//ip,port
		jedis.auth("YNETRedis192.168.65.56");//设置登录密码
		System.out.println(jedis.ping());//测试服务是否开启
		//jedis.bgrewriteaof(); 后台进行AOF
		//jedis.bgsave();后台进行快照备份
		//System.out.println(jedis.get("username"));//测试服务是否开启
		//jedis.flushAll();
		jedis.flushDB();
		/*jedis.flushDB();//清空当前数据库
		jedis.flushAll();//清空所有数据库
		System.out.println(jedis.getDB());
		
		//操作String
		jedis.set("name", "sky");
		jedis.append("name", " is ok");
		jedis.setnx("age", "28");
		jedis.set("sex", "man");
		jedis.del("sex");
		
		System.out.println(jedis.get("name"));
		System.out.println(jedis.get("age"));
		System.out.println(jedis.get("sex"));
		
		//操作hash
		jedis.hset("human", "name", "sky");
		jedis.hsetnx("human", "age", "28");//如果key里已经有field则不做修改
		jedis.hset("human", "sex", "man");
		Set<String> set = jedis.hkeys("human");
		Map<String,String> map = jedis.hgetAll("human");
		System.out.println(jedis.hget("human", "name"));
		
		//操作事务
		jedis.watch("student");//监控key,发生变化则事务回滚
		Transaction tran = jedis.multi();
		tran.hset("student","name","sky");
		tran.hset("student","age","28");
		tran.exec();//执行事务队列
		//tran.discard();取消事务
		
		jedis.flushDB();//清空当前数据库
		jedis.flushAll();//清空所有数据库
*/		jedis.close();
		//jedis.quit();//退出连接
		//jedis.shutdown();//慎用,关闭客户端
	}

}
