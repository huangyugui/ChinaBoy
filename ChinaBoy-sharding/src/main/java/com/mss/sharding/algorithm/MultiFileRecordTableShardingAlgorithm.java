package com.mss.sharding.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.MultipleKeysTableShardingAlgorithm;
import com.google.common.base.Joiner;

/**
 * 多分片键算法
 * 
 * @author sizm
 *
 */
public class MultiFileRecordTableShardingAlgorithm implements MultipleKeysTableShardingAlgorithm {

	@Override
	public Collection<String> doSharding(final Collection<String> availableTargetNames,
			final Collection<ShardingValue<?>> shardingValues) {
		//获取分表的suffix eg:0903_0   0903_1
		String tableSuffix = getShardingTableSuffix(shardingValues);
		List<String> result = new ArrayList<>();
		for (String tableName : availableTargetNames) {
			if (tableName.endsWith(tableSuffix)) {
				result.add(tableName);
			}
		}
		return result;
	}

	private String getShardingTableSuffix(final Collection<ShardingValue<?>> shardingValues) {
		Long id = null;
		String source = null;
		for (ShardingValue<?> each : shardingValues) {
			if (each.getColumnName().equals("id")) {
				if (each.getValue() != null) {
					id = (Long) each.getValue();
				}
			}
			if (each.getColumnName().equals("source_filefrom")) {
				source = (String) each.getValue();
			}
		}
		if (id != null && source != null) {
			return Joiner.on("_").join(source, id % 2);
		}
		return null;
	}
}
