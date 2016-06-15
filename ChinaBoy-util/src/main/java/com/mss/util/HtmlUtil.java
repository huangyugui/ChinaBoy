package com.mss.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * 
 * html生成工具
 * 
 * @author zt
 * @version 20151025
 *
 */
public class HtmlUtil {

	/**
	 * 构造HTTP POST交易表单的方法示例
	 * 
	 * @param action
	 *            表单提交地址
	 * @param hiddens
	 *            以MAP形式存储的表单键值
	 * @return 构造好的HTTP POST交易表单
	 */
	public static String createHtml(String action, String encoding, Map<String, String> hiddens) {
		StringBuffer sf = new StringBuffer();
		sf.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset="+encoding+"\"/></head><body>");
		sf.append("<form id = \"pay_form\" action=\"" + action
				+ "\" method=\"post\" >");
		if (null != hiddens && 0 != hiddens.size()) {
			Set<Entry<String, String>> set = hiddens.entrySet();
			Iterator<Entry<String, String>> it = set.iterator();
			while (it.hasNext()) {
				Entry<String, String> ey = it.next();
				String key = ey.getKey();
				String value = ey.getValue();
				sf.append("<input type=\"hidden\" name=\"" + key + "\" id=\""
						+ key + "\" value=\"" + value + "\"/>");
			}
		}
		sf.append("</form>");
		sf.append("</body>");
		sf.append("<script type=\"text/javascript\">");
		sf.append("document.all.pay_form.submit();");
		sf.append("</script>");
		sf.append("</html>");
		return sf.toString();
	}
	
	/**
	 * 创建微信公众号支付页面
	 * @param paramMap
	 * @return
	 */
	public static String createWeiXinHtml(String encoding, Map<String, String> paramMap){
		StringBuffer sf = new StringBuffer();
		sf.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset="+encoding+"\"/></head>");
		sf.append("<body>");
		sf.append("</body>");
		sf.append("<script type=\"text/javascript\">");
		sf.append("function onBridgeReady(){");
		sf.append("WeixinJSBridge.invoke(");
		sf.append("'getBrandWCPayRequest',{");
		sf.append("\"appId\":");
		sf.append("\""+paramMap.get("appId")+"\",");
		sf.append("\"timeStamp\":");
		sf.append("\""+paramMap.get("timeStamp")+"\",");
		sf.append("\"nonceStr\":");
		sf.append("\""+paramMap.get("nonceStr")+"\",");
		sf.append("\"package\":");
		sf.append("\""+paramMap.get("package")+"\",");
		sf.append("\"signType\":");
		sf.append("\""+paramMap.get("signType")+"\",");
		sf.append("\"paySign\":");
		sf.append("\""+paramMap.get("paySign")+"\"");
		sf.append("},");
		sf.append("function(res){");
		//sf.append("alert(res.err_code);");
		//sf.append("alert(res.err_msg);");
		//sf.append("alert(res.err_desc);");
		sf.append("var msg = \"\";");
		sf.append("if(res.err_msg == \"get_brand_wcpay_request:ok\")");
		sf.append("{");
		sf.append("msg = \"支付成功!\";");
		sf.append("}");
		sf.append("else if(res.err_msg == \"get_brand_wcpay_request:cancel\")");
		sf.append("{");
		sf.append("msg = \"支付取消!\";");
		sf.append("}");
		sf.append("else if(res.err_msg == \"get_brand_wcpay_request:fail\")");
		sf.append("{");
		sf.append("msg = \"支付失败!\";");
		sf.append("}");
		sf.append("window.location.href = \"weixin/frontReceive\";");
		sf.append("}");
		sf.append(");");
		sf.append("}");
		sf.append("if (typeof WeixinJSBridge == \"undefined\"){");
		sf.append(" if( document.addEventListener ){");
		sf.append("document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);");
		sf.append("}else if (document.attachEvent){");
		sf.append("document.attachEvent('WeixinJSBridgeReady', onBridgeReady); ");
		sf.append("document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);");
		sf.append("}");
		sf.append("}else{");
		sf.append("onBridgeReady();");
		sf.append("}");
		sf.append("</script>");
		sf.append("</html>");
		return sf.toString();
	}
	
	
}
