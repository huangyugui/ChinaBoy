package com.mss.web.control;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//标明为control
public class HttpControl extends BaseControl{
	
    @Autowired  
    private  HttpServletRequest req;
    
    @Autowired  
    private  HttpServletResponse res;  
    
	@RequestMapping(value="/HttpServer")//该方法的url
	public void test()throws IOException{
			System.out.println("----------------------------Welcome to ChinaBoy Http Server----------------------------------");
			
			System.out.println("----------------------------Handle Request Message----------------------------------");
			
			StringBuffer sbRes = new StringBuffer();
			
			req.setCharacterEncoding("UTF8");
			
			//获取请求的所有报文头信息并打印
			Enumeration<String> enumHeader = req.getHeaderNames();
			while(enumHeader.hasMoreElements()){
				String key = enumHeader.nextElement();
				System.out.println("---------------------req header ["+key+"] = {"+req.getHeader(key)+"}");
				sbRes.append("---------------------req header ["+key+"] = {"+req.getHeader(key)+"}");
				sbRes.append("\n");
			}
			
			//获取请求的所有参数信息并打印
			Enumeration<String> enumParam = req.getParameterNames();
			while(enumParam.hasMoreElements()){
				String key = enumParam.nextElement();
				System.out.println("---------------------req param ["+key+"] = {"+URLDecoder.decode(req.getParameter(key),"utf8")+"}");
				sbRes.append("---------------------req param ["+key+"] = {"+URLDecoder.decode(req.getParameter(key),"utf8")+"}");
				sbRes.append("\n");
			}
			
			//获取请求流信息并打印
			InputStream is = req.getInputStream();
			byte[] reqByte = new byte[100];
			StringBuffer sbReq = new StringBuffer();
			while(true){
				//读取的数据会依次放入数组,未放满的话之前的数据还会存在(前提是数组未初始化)
				//所以需要根据读取的字节数num来创建新对象如String
				int num = is.read(reqByte);
				//数组未读满的话,还会在读一次直到num=-1
				if(num==-1){
					break;
				}
				//约定好客户端与服务端的编码
				String str = new String(reqByte,0,num,"utf8");
				sbReq.append(str);
			}
			System.out.println("---------------------req InputStream = {"+sbReq+"}");
			sbRes.append("---------------------req InputStream = {"+sbReq+"}");
			
			System.out.println("----------------------------Handle Response Message----------------------------------");
			//HttpServletResponse res = ServletActionContext.getResponse();
			
			//添加/设置响应报文头信息
			res.addHeader("test-header-admin-name", "sky");
			res.addHeader("test-header-admin-sex", "man");
			res.addHeader("test-header-admin-age", "28");
			res.setCharacterEncoding("utf8");
			
			//输出响应信息,输出流
			res.setCharacterEncoding("UTF8");
			res.setContentType("text/plain");
			res.getWriter().write(sbRes.toString());
			res.getWriter().flush();
			res.getWriter().close();
			
			System.out.println("----------------------------Goodbye to ChinaBoy Http Server----------------------------------");
		
	}
	
}
