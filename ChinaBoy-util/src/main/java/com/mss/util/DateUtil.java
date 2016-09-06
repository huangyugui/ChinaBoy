package com.mss.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * Date工具类
 * 
 * @author zt
 * @version 20140903
 * 
 */
public class DateUtil {

	public static void main(String[] args) throws Exception{
		System.out.println(formatDate(new Date(),"yyyyMMddHHmmssSSS"));
	}
	
	/**
	 * 格式化日期,yyyy:年;MM:月;dd:日;HH:24小时制;hh:12小时制;mm:分钟;ss:秒;SSS:毫秒
	 * 默认输出格式:yyyy/MM/dd HH:mm:ss
	 * @param date 日期
	 * @param format 输出日期格式
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		SimpleDateFormat sdf = null;
		if(!StringUtil.checkNotEmpty(format)){
			sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		}else{
			sdf = new SimpleDateFormat(format);
		}
		return sdf.format(date);
	}
	
	/**
	 * String2Date
	 * @param date 日期,例如20140903
	 * @param format date日期格式,例如yyyyMMdd
	 * @return
	 * @throws ParseException 
	 */
	public static Date parseDate(String date, String format) throws ParseException {
		return new SimpleDateFormat(format).parse(date);
	}
	
	/**
	 * 获取日期为星期几
	 * 美历,1月:0;2月:1;3月:2;4月:3;5月:4;6月:5;7月:6;8月:7;9月:8;10月:9;11月:10;12月:11;
	 * 美历,周日:1;周一:2;周二:3;周三:4;周四:5;周五:6;周六:7;
	 * @param date 日期
	 * @param format date日期格式
	 * @return 周日:0;周一:1;周二:2;周三:3;周四:4;周五:5;周六:6;
	 * @throws ParseException 
	 */
	public static int getWeek(String date, String format) throws ParseException {
		return getWeek(parseDate(date, format));
	}
	
	/**
	 * 获取日期为星期几
	 * 美历,1月:0;2月:1;3月:2;4月:3;5月:4;6月:5;7月:6;8月:7;9月:8;10月:9;11月:10;12月:11;
	 * 美历,周日:1;周一:2;周二:3;周三:4;周四:5;周五:6;周六:7;
	 * @param date 日期
	 * @return 周日:0;周一:1;周二:2;周三:3;周四:4;周五:5;周六:6;
	 * @throws Exception 
	 */
	public static int getWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK)-1;
	}
	
	/**
	 * 获取yesterday
	 * @param date 日期
	 * @param format date日期格式
	 * @return
	 * @throws ParseException 
	 */
	public static String getYesterday(String date, String format) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(date, format));
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-1);
		return formatDate(calendar.getTime(), "yyyyMMdd");
	}
	
	/**
	 * 获取yesterday
	 * @param date 日期
	 * @param format 输出日期格式
	 * @return
	 * @throws ParseException 
	 */
	public static String getYesterday(Date date, String format) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-1);
		return formatDate(calendar.getTime(), format);
	}
	
	/**
	 * 获取tomorrow
	 * @param date 日期
	 * @param format date日期格式
	 * @return
	 * @throws ParseException 
	 */
	public static String getTomorrow(String date, String format) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(date, format));
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)+1);
		return formatDate(calendar.getTime(), "yyyyMMdd");
	}
	
	/**
	 * 获取tomorrow
	 * @param date 日期
	 * @param format 输出日期格式
	 * @return
	 * @throws ParseException 
	 */
	public static String getTomorrow(Date date, String format) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)+1);
		return formatDate(calendar.getTime(), format);
	}

	/**
	 * 获取某月月底日期
	 * @param date 日期
	 * @param format 输出日期格式
	 * @return
	 */
	public static String getLastDay(Date date, String format){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = 0;
		switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				day=31;
				break;
			case 2:
				if(isLeapYear(date)){
					day=29;
				}else{
					day=28;
				}
				break;
			default:
				day = 30;
				break;
		}
		calendar.set(Calendar.DATE, day);
		return  formatDate(calendar.getTime(), format);
	}
	
	/**
	 * 获取某月月底日期
	 * @param date 日期
	 * @param format date日期格式
	 * @return
	 * @throws ParseException 
	 */
	public static String getLastDay(String date, String format) throws ParseException{
		return  getLastDay(parseDate(date, format),format);
	}
	
	/**
	 * 判断是否为闰年
	 * @param date 日期
	 * @return
	 */
	public static boolean isLeapYear(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		return (year%4==0&&year%100!=0)||year%400==0;
	}
	
	/**
	 * 判断是否为闰年
	 * @param date 日期
	 * @param format 日期格式
	 * @return
	 * @throws ParseException
	 */
	public static boolean isLeapYear(String date, String format) throws ParseException{
		return isLeapYear(parseDate(date, format));
	}
	
}
