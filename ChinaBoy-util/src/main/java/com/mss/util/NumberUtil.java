package com.mss.util;

import java.text.DecimalFormat;

/**
 * 
 * Number工具类
 * 
 * @author zt
 * @version 20140916
 * 
 */
public class NumberUtil {

	public static void main(String[] args){
		System.out.println(formatNumber(1,"00.00"));
	}
	
	/**
	 * 格式化数字
	 * 0:一个数字;#:一个数字不包含0;.:小数分隔占位符;,:分组分隔占位符;-:负数前缀;%:乘以100百分数显示
	 * @param num 数字
	 * @param format 格式
	 * @return
	 */
	public static String formatNumber(String num,String format){
		return new DecimalFormat(format).format(Double.parseDouble(num));
	}
	
	/**
	 * 格式化数字
	 * 0:一个数字;#:一个数字不包含0;.:小数分隔占位符;,:分组分隔占位符;-:负数前缀;%:乘以100百分数显示
	 * @param num 数字
	 * @param format 格式
	 * @return
	 */
	public static String formatNumber(Number num,String format){
		return new DecimalFormat(format).format(num);
	}
}
