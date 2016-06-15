package com.mss.util;

/**
 * String工具类
 * @author zt
 * @version 20140829
 */

public class StringUtil {

	/**
	 * 判断字符串非空
	 * @param str
	 * @return
	 */
	public static boolean checkNotEmpty(String str){
		if(str==null){
			return false;
		}else if(str.equals("")){
			return false;
		}else{
			return true;
		}
	}
	
}
