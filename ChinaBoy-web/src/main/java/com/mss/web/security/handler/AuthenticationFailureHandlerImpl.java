package com.mss.web.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException auth)
			throws IOException, ServletException {
		response.sendRedirect(request.getContextPath());
	}

}
