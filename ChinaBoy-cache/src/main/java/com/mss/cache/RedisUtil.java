package com.mss.cache;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.mss.util.SerializeUtil;

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
	 * 存取key-value值
	 * @param key
	 * @param value
	 * @throws IOException
	 */
	public void save(final String key, Object value) throws IOException {

		final byte[] vbytes = SerializeUtil.serialize(value);
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(redisTemplate.getStringSerializer().serialize(key), vbytes);
				return null;
			}
		});
	}
	
	/**
	 * 存取key-value值,并设置失效时间
	 * @param key
	 * @param value
	 * @param seconds
	 * @throws IOException
	 */
	public void save(final String key, Object value, final long seconds) throws IOException {

		final byte[] vbytes = SerializeUtil.serialize(value);
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.setEx(redisTemplate.getStringSerializer().serialize(key), seconds, vbytes);
				return null;
			}
		});
	}

	/**
	 * 获取key-value值
	 * @param key
	 * @param elementType
	 * @return
	 * @throws Exception
	 */
	public <T> T get(final String key, Class<T> elementType) throws Exception {
		return redisTemplate.execute(new RedisCallback<T>() {
			@SuppressWarnings("unchecked")
			@Override
			public T doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] keybytes = redisTemplate.getStringSerializer().serialize(key);
				if (connection.exists(keybytes)) {
					byte[] valuebytes = connection.get(keybytes);
					T value;
					try {
						value = (T) SerializeUtil.deserialize(valuebytes);
						return value;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return null;
			}
		});
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
	 * 通过patten删除key-value
	 * @param patten
	 */
	public void delByPatten(String patten){
		redisTemplate.delete(redisTemplate.keys(patten));
	}
	
	/**
	 * 通过集合删除key-value
	 * @param keys
	 */
	public void delByCollection(Collection<Serializable> keys){
		redisTemplate.delete(keys);
	}
	
	/**
	 * 通过key删除key-value
	 * @param key
	 */
	public void delByKey(String key){
		redisTemplate.delete(key);
	}
	
	
}
