package com.mss.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 描述：spring security过滤器First
 * 
 * @author zt
 * @version 1.0
 * @date 2016-08-19
 */
@Component
public class FirstFilter implements Filter{
	
	private static Logger logger = Logger.getLogger(FirstFilter.class);
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		logger.info("[spring security]-[filter]-First filter handle success!");
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	

}
