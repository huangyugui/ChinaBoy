package com.mss.sharding.algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.MultipleKeysTableShardingAlgorithm;

/**
 * 
 * 表多分片算法
 * 
 * @author zt
 * @version 20170306
 *
 */
public class MultipleKeysTableSharding implements MultipleKeysTableShardingAlgorithm {

	@Override
	public Collection<String> doSharding(Collection<String> availableTargetNames,
			Collection<ShardingValue<?>> shardingValues) {
		List<String> result = new ArrayList<>();
		for(String name:availableTargetNames){
			long id = 0;
			for(ShardingValue<?> value:shardingValues){
				if(value.getColumnName().equals("id")){
					id = id + (int)value.getValue();
				}else{
					id = id + ((BigDecimal)value.getValue()).intValue();
				}
			}
			if(name.endsWith(Math.abs(id%2)+"")){
				result.add(name);
				return result;
			}
		}
		return null;
	}

}
