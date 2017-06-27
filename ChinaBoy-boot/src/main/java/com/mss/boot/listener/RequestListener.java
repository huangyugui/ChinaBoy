package com.mss.boot.listener;

import java.util.Enumeration;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class RequestListener implements ServletRequestAttributeListener,
		ServletRequestListener {
	
	private static Logger logger = Logger.getLogger(RequestListener.class);
	
	@Override
	public void requestDestroyed(ServletRequestEvent event) {
	}

	@Override
	public void requestInitialized(ServletRequestEvent event) {
		
		//打印所有请求参数
		try{
		    
			HttpServletRequest req = (HttpServletRequest) event.getServletRequest();
			req.setCharacterEncoding("UTF8");
			
		    //获取请求的所有报文头信息并打印
			Enumeration<String> enumHeader = req.getHeaderNames();
			StringBuffer sbHeader = new StringBuffer();
			sbHeader.append("\n");
			sbHeader.append("##################################################");
			sbHeader.append("\n");
			sbHeader.append("##########接收请求,url={"+req.getRequestURL()+"}");
			sbHeader.append("\n");
			sbHeader.append("##########接收请求,报文头信息={");
			sbHeader.append("\n");
			while(enumHeader.hasMoreElements()){
				String key = enumHeader.nextElement();
				sbHeader.append("["+key+"] = {"+req.getHeader(key)+"}, ");
				sbHeader.append("\n");
			}
			sbHeader.append("}");
			sbHeader.append("\n");
			
			//获取请求的所有参数信息并打印
			Enumeration<String> enumParam = req.getParameterNames();
			StringBuffer sbParam = new StringBuffer();
			sbParam.append("##########接收请求,参数信息={");
			sbParam.append("\n");
			while(enumParam.hasMoreElements()){
				String key = enumParam.nextElement();
				sbParam.append("["+key+"] = {"+req.getParameter(key).replace("\r|\n|\t", "")+"}, ");
				sbParam.append("\n");
			}
			sbParam.append("}");
			sbParam.append("\n");
			
			
			sbHeader.append(sbParam);
			sbHeader.append("##################################################");
			logger.info(new String(sbHeader));
			
		}catch(Exception e){
			logger.info("##########接收请求异常,信息={"+e.getMessage()+"}");
		}
		
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent event) {

	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent event) {

	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent event) {

	}

}
