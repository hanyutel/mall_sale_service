package cn.hanyuweb.util;

import java.io.IOException;
import java.util.Properties;

public class LoadPropertyUtil {


	public static String load(String propertyName, String key) {
		Properties properties=new Properties();
		String url = null;
		try {
			properties.load(LoadPropertyUtil.class.getClassLoader().getResourceAsStream(propertyName));
			url = (String) properties.get(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return url;
	}

}
