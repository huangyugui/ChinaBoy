package com.mss.cache;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 
 * Redis操作工具
 * 
 * @author zt
 * @version 20160907
 *
 */
@Component
public class RedisUtil {
	
	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;
	
	/**
	 * 创建缓存
	 * @param key
	 * @param value
	 */
	public void save(Serializable key, Serializable value){
		redisTemplate.opsForValue().set(key, value);
	}
	
	/**
	 * 创建缓存并设置失效时间
	 * @param key
	 * @param value
	 * @param timeout
	 * @param unit
	 */
	public void save(Serializable key, Serializable value, long timeout, TimeUnit unit){
		redisTemplate.opsForValue().set(key, value, timeout, unit);
	}
	
	/**
	 * 获取缓存
	 * @param key
	 * @param elementType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(Serializable key, Class<T> elementType){
		return (T) redisTemplate.opsForValue().get(key);
	}
	
	/**
	 * 获取key集合
	 * @param patten
	 * @return
	 */
	public Set<Serializable> keys(String patten){
		return redisTemplate.keys(patten);
	}
	
	/**
	 * 通过patten通配key删除缓存
	 * @param patten
	 */
	public void delByPatten(String patten){
		redisTemplate.delete(redisTemplate.keys(patten));
	}
	
	/**
	 * 通过key集合删除缓存
	 * @param keys
	 */
	public void delByCollection(Collection<Serializable> keys){
		redisTemplate.delete(keys);
	}
	
	/**
	 * 通过key删除缓存
	 * @param key
	 */
	public void delByKey(String key){
		redisTemplate.delete(key);
	}
	
	
}
