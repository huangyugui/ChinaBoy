package com.mss.job.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;

/**
 * 
 * 测试ElasticJobListener
 * 
 * @author zhangtao
 * @version 20170411
 *
 */
public class TestElasticJobListener implements ElasticJobListener {

	@Override
	public void afterJobExecuted(ShardingContexts shardingContext) {
		System.out.println("------------------after job ["+shardingContext.getTaskId()+"|"+shardingContext.getJobName()+"]--------------------");
	}

	@Override
	public void beforeJobExecuted(ShardingContexts shardingContext) {
		System.out.println("------------------before job ["+shardingContext.getTaskId()+"|"+shardingContext.getJobName()+"]--------------------");
	}

}
