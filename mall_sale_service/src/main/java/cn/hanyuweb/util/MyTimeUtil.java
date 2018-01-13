package cn.hanyuweb.util;

import java.util.Calendar;
import java.util.Date;

import com.mysql.fabric.xmlrpc.base.Data;

public class MyTimeUtil {

	public static void main(String[] args) {
		Calendar calendar =Calendar.getInstance();
		Date time = calendar.getTime();
		System.out.println(time);
		calendar.add(Calendar.DATE, 3);
		System.out.println(calendar.getTime());
	}
	public static Date getTime(int day){
		Calendar calendar =Calendar.getInstance();
		calendar.add(Calendar.DATE, day);
		Date time = calendar.getTime();
		return time;
	}
}
