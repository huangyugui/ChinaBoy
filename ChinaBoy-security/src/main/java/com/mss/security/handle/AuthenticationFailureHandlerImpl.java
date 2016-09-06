package com.mss.security.handle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 描述：授权登录失败之后处理器
 * 
 * @author zt
 * @version 1.0
 * @date 2016-08-05
 */
@Component("authenticationFailureHandler")
public class AuthenticationFailureHandlerImpl implements
		AuthenticationFailureHandler {

	private static Logger logger = Logger.getLogger(AuthenticationFailureHandlerImpl.class);
			
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException auth)
			throws IOException, ServletException {
		logger.info("[Spring Security Auth Failure!]");
		response.sendRedirect(request.getContextPath());
	}

}
