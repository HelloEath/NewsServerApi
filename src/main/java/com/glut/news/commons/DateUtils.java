package com.glut.news.commons;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public DateUtils() {
		// TODO Auto-generated constructor stub
	}
	//获取当前数据库时间
	public static java.sql.Date getDate(){
		Date day=new Date();    
      java.sql.Date date=new java.sql.Date(day.getTime());

		return date;
		
	}
	 //获取当前系统时间：格式：按照传入的格式定
    public static String formatDate_getCurrentDateByF(String f){
        SimpleDateFormat sDateFormat = new SimpleDateFormat(f);
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }
	//获取当前系统时间:可以对每个时间域单独修改   
	public static Calendar getDate2(){
		Calendar c = Calendar.getInstance();
		return c;
	}

}
