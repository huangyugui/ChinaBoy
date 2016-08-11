package com.mss.web.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 描述：授权登录成功之后处理器
 * 
 * @author zt
 * @version 1.0
 * @date 2016-08-05
 */
@Component("authenticationSuccessHandler")
public class AuthenticationSuccessHandlerImpl implements
		AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth) throws IOException,
			ServletException {
		response.sendRedirect(request.getContextPath());
	}

}