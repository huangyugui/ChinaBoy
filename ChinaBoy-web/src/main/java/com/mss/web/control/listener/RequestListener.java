package com.mss.web.control.listener;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.mss.util.DateUtil;
import com.mss.util.StringUtil;

/**
 * 
 * 网关request监听器
 * 
 * @author zt
 * @version 20160304
 *
 */
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
			sbHeader.append(DateUtil.formatDate(new Date(), "yyyy/MM/dd HH:mm:ss")+"##########网关接收请求,报文头信息={");
			while(enumHeader.hasMoreElements()){
				String key = enumHeader.nextElement();
				sbHeader.append("["+key+"] = {"+req.getHeader(key)+"}, ");
			}
			sbHeader.append("}");
			
			//获取请求的所有参数信息并打印
			Enumeration<String> enumParam = req.getParameterNames();
			StringBuffer sbParam = new StringBuffer();
			while(enumParam.hasMoreElements()){
				String key = enumParam.nextElement();
				sbParam.append("["+key+"] = {"+req.getParameter(key).replace("\r|\n|\t", "")+"}, ");
				sbParam.append("\n");
			}
			
			if(StringUtil.checkNotEmpty(new String(sbParam))){
				logger.info("##################################################");
				logger.info(sbHeader+"");
				logger.info("##########网关接收请求,参数信息={"+sbParam+"}");
				logger.info("##################################################");
			}
			
		}catch(Exception e){
			logger.info("##########网关接收请求异常,信息={"+e.getMessage()+"}");
		}
		
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent event) {
		// TODO Auto-generated method stub

	}

}
