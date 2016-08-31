package com.mss.core.task;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * quartz测试
 * @author zt
 * @version 20160531
 */
@Component
public class TestTask extends QuartzJobBean {
	
	public static Log logger = LogFactory.getLog(TestTask.class);

	public static void main(String[] args){
		String s = "12.343";
		System.out.println(new BigDecimal(s).toString());
	}
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("------------------task start --------------------");
	}
}
