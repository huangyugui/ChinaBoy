package com.mss.sharding.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.MultipleKeysDatabaseShardingAlgorithm;

/**
 * 
 * 数据库多分片算法
 * 
 * @author zt
 * @version 20170306
 *
 */
public class MultipleKeyDatabaseSharding implements MultipleKeysDatabaseShardingAlgorithm {
	
	@Override
	public Collection<String> doSharding(Collection<String> availableTargetNames,
			Collection<ShardingValue<?>> shardingValues) {
		List<String> result = new ArrayList<>();
		for(String name:availableTargetNames){
			long time = 0;
			for(ShardingValue<?> value:shardingValues){
				Date date = (Date)value.getValue();
				time = date.getTime();
			}
			if(name.endsWith(time%2+"")){
				result.add(name);
				return result;
			}
		}
		
		return null;
	}

}
