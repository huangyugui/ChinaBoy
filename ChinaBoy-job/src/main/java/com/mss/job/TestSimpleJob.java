package com.mss.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * 
 * 测试当当Elastic SimpleJob
 * 
 * @author zhangtao
 * @version 20170330
 *
 */
public class TestSimpleJob implements SimpleJob{

	@Override
	public void execute(ShardingContext context) {
		System.out.println("------------------Elastic SimpleJob Start --------------------");
	}

}
