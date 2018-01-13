package cn.hanyuweb.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

import net.sf.json.JSONArray;

public class MyJsonUtil {

	public static <T> String obj_to_json(T t){
		Gson gson = new Gson();
		String json = gson.toJson(t);
		 try {
			json = URLEncoder.encode(json,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return json;
	}
	public static <T> T json_to_obj (String json,Class<T> t){
		String encode ="";
		try {
			encode= URLDecoder.decode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		T fromJson = gson.fromJson(encode, t);
		return fromJson;
	}
	
	public static <T> List<T> json_to_list(String json,Class<T> t){
		String encode ="";
		if(StringUtils.isBlank(json)){
			return null;
		}else{
			try {
				encode= URLDecoder.decode(json, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			JSONArray jsonArray = JSONArray.fromObject(encode);
			@SuppressWarnings("unchecked")
			List<T> collection = (List<T>)JSONArray.toCollection(jsonArray, t);
			
			return collection;
		}
	}
	public static <T> String list_to_json(List<T> list){
		Gson gson = new Gson();
		String json = gson.toJson(list);
		try {
			json= URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
}
