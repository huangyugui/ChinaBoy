package com.mss.job;

import java.util.List;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

/**
 * 
 * 测试当当Elastic DataflowJob
 * 
 * @author zhangtao
 * @version 20170330
 *
 */
public class TestDataflowJob implements DataflowJob<Object>{

	@Override
	public List<Object> fetchData(ShardingContext context) {
		return null;
	}

	@Override
	public void processData(ShardingContext context, List<Object> list) {
		
	}

}
