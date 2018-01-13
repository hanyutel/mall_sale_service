package cn.hanyuweb.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.hanyuweb.bean.T_MALL_SKU_ATTR_VALUE;
import redis.clients.jedis.Jedis;

public class MyCacheUtil {

	public static <T> List<T> get_list(String key, Class<T> t) {
		List<T> list=new ArrayList<T>();
		try {
			Jedis jedis = RedisPoolUtil.getJedis();
			Set<String> zrange = jedis.zrange(key, 0, -1);
			for (String string : zrange) {
				T obj = MyJsonUtil.json_to_obj(string, t);
				list.add(obj);
			}
		} catch (Exception e) {
			return null;
		}
		return list;
	}

	public static <T> void save_list(String key, List<T> t) {
		try {
			Jedis jedis = RedisPoolUtil.getJedis();
			for (int i=0;i<t.size();i++) {
				String json = MyJsonUtil.obj_to_json(t.get(i));
				jedis.zadd(key,i,json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
