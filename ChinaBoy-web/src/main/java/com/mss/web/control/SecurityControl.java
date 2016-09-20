package com.mss.web.control;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * spring security 登录登出测试control
 * @author zt
 * @version 20160816
 */
@Controller
@Scope("prototype")
public class SecurityControl extends BaseControl{
			
	@RequestMapping(value="/toSecurityLogin")
	public String login(){
		return "securityLogin";
	}
	
}
