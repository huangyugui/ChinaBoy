package com.mss.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 
 * https请求工具类
 * 
 * @author zt
 * @version 20151019
 *
 */
public class HttpsUtil {

	/**
	 * 请求地址
	 */
	private String url;
	
	/**
	 * 请求编码
	 */
	private String encoding;
	
	/**
	 * 通信连接超时时间
	 */
	private int connectionTimeout = 30000;
	
	/**
	 * 通信读取超时时间
	 */
	private int readTimeOut = 30000;
	
	/**
	 * 请求类型application/x-www-form-urlencoded
	 */
	private static final String CONTENT_TYPR_FORM = "application/x-www-form-urlencoded";
	
	/**
	 * 请求类型application/octet-stream
	 */
	private static final String CONTENT_TYPR_STREAM = "application/octet-stream";
	
	private HttpsURLConnection httpsURLConnection;
	
	private static TrustManager easyTrustManager = new X509TrustManager() { 

	    @Override 
	    public X509Certificate[] getAcceptedIssuers() { 
	        return null; 
	    } 

	    @Override 
	    public void checkServerTrusted(X509Certificate[] chain, String authType) 
	    throws CertificateException { 
	    } 

	    @Override 
	    public void checkClientTrusted(X509Certificate[] chain, String authType) 
	    throws CertificateException { 
	    } 
	};
	
	/**
	 * 构造方法
	 * @param url
	 * @param encoding
	 */
	public HttpsUtil(String url, String encoding) {
		this.url = url;
		this.encoding = encoding;
	}

	/**
	 * 创建get连接
	 *
	 * @return
	 * @throws ProtocolException
	 */
	private HttpsURLConnection createGetConnection() throws Exception {
		URL httpUrl = null;
		httpUrl = new URL(url);
		httpsURLConnection = (HttpsURLConnection) httpUrl.openConnection();
		httpsURLConnection.setConnectTimeout(this.connectionTimeout);// 连接超时时间
		httpsURLConnection.setReadTimeout(this.readTimeOut);// 读取结果超时时间
		httpsURLConnection.setDoInput(true); // 可读
		
		SSLContext sslcontext = SSLContext.getInstance("TLS");
		sslcontext.init(null, new TrustManager[] { easyTrustManager }, null);
		httpsURLConnection.setSSLSocketFactory(sslcontext.getSocketFactory());
		
		return httpsURLConnection;
	}
	
	/**
	 * 创建post连接
	 *
	 * @return
	 * @throws ProtocolException
	 */
	private HttpsURLConnection createPostConnection() throws Exception {
		URL httpUrl = null;
		httpUrl = new URL(url);
		httpsURLConnection = (HttpsURLConnection) httpUrl.openConnection();
		httpsURLConnection.setConnectTimeout(this.connectionTimeout);// 连接超时时间
		httpsURLConnection.setReadTimeout(this.readTimeOut);// 读取结果超时时间
		httpsURLConnection.setDoInput(true); // 可读
		httpsURLConnection.setDoOutput(true); // 可写
		httpsURLConnection.setUseCaches(false);// 取消缓存
		httpsURLConnection.setRequestMethod("POST");//请求方法POST
		
		SSLContext sslcontext = SSLContext.getInstance("TLS");
		sslcontext.init(null, new TrustManager[] { easyTrustManager }, null);
		httpsURLConnection.setSSLSocketFactory(sslcontext.getSocketFactory());
		
		return httpsURLConnection;
	}
	
	/**
	 * 发送application/octet-stream请求
	 * @param msg
	 * @return
	 */
	public String sendStream(String msg) throws Exception {
		
		OutputStream os = null;
		InputStream is = null;
		String response = "";
		
		try{
			//创建请求报文头信息
			createPostConnection();
			httpsURLConnection.setRequestProperty("Content-type", CONTENT_TYPR_STREAM+";charset=" + encoding);
			
			//与服务器建立TCP连接(可以不写),必须在头信息设置之后
			httpsURLConnection.connect();
			
			//输出请求信息,信息只是写入本机内存并没有发送
			os = httpsURLConnection.getOutputStream();
			os.write(msg.getBytes(encoding));
			os.flush();
			os.close();
			
			//发送并获取响应信息
			response = response(is);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(os!=null){//如果输出流没关闭(异常等造成)，关闭输出流
				os.close();
			}
			if(is!=null){//关闭输入流
				is.close();
			}
			if(httpsURLConnection!=null){//关闭连接
				httpsURLConnection.disconnect();
			}
		}
		
		return response;
	}
	
	/**
	 * 发送application/x-www-form-urlencoded请求
	 * @param msg
	 * @return
	 */
	public String sendForm(Map<String,String> map) throws Exception {
		
		OutputStream os = null;
		InputStream is = null;
		String response = "";
		
		try{
			//创建请求报文头信息
			createPostConnection();
			httpsURLConnection.setRequestProperty("Content-type", CONTENT_TYPR_FORM+";charset=" + encoding);
			
			//与服务器建立TCP连接(可以不写),必须在头信息设置之后
			httpsURLConnection.connect();
			
			//输出请求信息,信息只是写入本机内存并没有发送
			os = httpsURLConnection.getOutputStream();
			os.write(getFormParams(map).getBytes(encoding));
			os.flush();
			os.close();
			
			//发送并获取响应信息
			response = response(is);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(os!=null){//如果输出流没关闭(异常等造成)，关闭输出流
				os.close();
			}
			if(is!=null){//关闭输入流
				is.close();
			}
			if(httpsURLConnection!=null){//关闭连接
				httpsURLConnection.disconnect();
			}
		}
		
		return response;
	}
	
	/**
	 * 发送application/octet-stream请求
	 * @param msg
	 * @return
	 */
	public String sendGet() throws Exception {
		
		OutputStream os = null;
		InputStream is = null;
		String response = "";
		
		try{
			//创建请求报文头信息
			createGetConnection();
			
			//与服务器建立TCP连接(可以不写),必须在头信息设置之后
			httpsURLConnection.connect();
			
			//发送并获取响应信息
			response = response(is);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(os!=null){//如果输出流没关闭(异常等造成)，关闭输出流
				os.close();
			}
			if(is!=null){//关闭输入流
				is.close();
			}
			if(httpsURLConnection!=null){//关闭连接
				httpsURLConnection.disconnect();
			}
		}
		
		return response;
	}
	
	/**
	 * 获取响应信息
	 * @return
	 * @throws Exception 
	 */
	private String response(InputStream is) throws Exception{
		//正式发送http请求并获得服务器响应,响应信息一般都是流
		if(200 == httpsURLConnection.getResponseCode()) {//请求成功
			is = httpsURLConnection.getInputStream();
		}else {
			is = httpsURLConnection.getErrorStream();
		}
		return new String(getBytes(is), encoding);
	}
	
	/**
	 * Map转换成key=value&key=value字符串
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	private String getFormParams(Map<String,String> map) throws Exception{
		StringBuffer sb = new StringBuffer();
		Set<String> keySet = map.keySet();
		for(String str:keySet){
			sb.append(str);
			sb.append("=");
			if(map.get(str)==null){
				sb.append("");
			}else{
				sb.append(URLEncoder.encode(map.get(str),encoding));//URLEncoder编码
			}
			sb.append("&");
		}
		return sb.substring(0,sb.length()-1);
	}
	
	/**
	 * 输入流转换成byte[]
	 * @param in
	 * @return
	 * @throws IOException
	 */
	private byte[] getBytes(InputStream is) throws Exception {
		byte[] buf = new byte[1024];
		int length = 0;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while ((length = is.read(buf, 0, buf.length)) > 0) {
			baos.write(buf, 0, length);
		}
		baos.flush();
		return baos.toByteArray();
	}
	
	public static void main(String[] args) throws Exception{
		String msg = new HttpsUtil("https://www.baidu.com","UTF8").sendStream("test");
		System.out.println(msg);
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getReadTimeOut() {
		return readTimeOut;
	}

	public void setReadTimeOut(int readTimeOut) {
		this.readTimeOut = readTimeOut;
	}

	public HttpsURLConnection getHttpsURLConnection() {
		return httpsURLConnection;
	}

	public void setHttpsURLConnection(HttpsURLConnection httpURLConnection) {
		this.httpsURLConnection = httpURLConnection;
	}
	
	
}
