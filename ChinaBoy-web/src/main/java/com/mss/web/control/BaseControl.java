package com.mss.web.control;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Control父类
 * @author zt
 * @version 20150805
 */
public class BaseControl {

	private static Logger logger = Logger.getLogger(BaseControl.class);
			
	/**
	 * 获取spring security 用户名
	 * @return
	 */
	public String getCurrentUsername(){
		try{
			String userName;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(principal instanceof UserDetails){
				userName = ((UserDetails) principal).getUsername();
			}else if(principal instanceof Principal){
				userName = ((Principal) principal).getName();
			}else{
				userName = String.valueOf(principal);
			}
			logger.error("[UserDetail]-用户名："+userName);
			return userName;
		}catch(Exception e){
			logger.error("[UserDetail]-获取用户名异常，信息："+e.getMessage());
			return null;
		}
	}
	
}
