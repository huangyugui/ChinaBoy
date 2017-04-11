package com.mss.job.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.AbstractDistributeOnceElasticJobListener;

/**
 * 
 * 测试节点ElasticJobListener
 * 
 * @author zhangtao
 * @version 20170411
 *
 */
public class TestDistributeOnceElasticJobListener extends AbstractDistributeOnceElasticJobListener {

	public TestDistributeOnceElasticJobListener(long startedTimeoutMilliseconds, long completedTimeoutMilliseconds) {
		super(startedTimeoutMilliseconds, completedTimeoutMilliseconds);
	}

	@Override
	public void doAfterJobExecutedAtLastCompleted(ShardingContexts shardingContext) {
		System.out.println("------------------node after job ["+shardingContext.getTaskId()+"|"+shardingContext.getJobName()+"]--------------------");
	}

	@Override
	public void doBeforeJobExecutedAtLastStarted(ShardingContexts shardingContext) {
		System.out.println("------------------node after job ["+shardingContext.getTaskId()+"|"+shardingContext.getJobName()+"]--------------------");
	}

}
