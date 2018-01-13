package cn.hanyuweb.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.hanyuweb.bean.MODEL_T_SKU_ATTR_VALUE;
import cn.hanyuweb.bean.OBJECT_T_MALL_ATTR;
import cn.hanyuweb.bean.OBJECT_T_MALL_SKU;
import cn.hanyuweb.bean.OBJECT_T_MALL_SKU_DETAIL;
import cn.hanyuweb.bean.OBJECT_T_MALL_SKU_KEYWORDS;
import cn.hanyuweb.bean.T_MALL_SKU;
import cn.hanyuweb.bean.T_MALL_SKU_ATTR_VALUE;
import cn.hanyuweb.bean.T_MALL_VALUE;
import cn.hanyuweb.service.AttrServiceInf;
import cn.hanyuweb.service.SearchServiceInf;
import cn.hanyuweb.util.LoadPropertyUtil;
import cn.hanyuweb.util.MyCacheUtil;
import cn.hanyuweb.util.MyHttpGetUtil;
import cn.hanyuweb.util.MyJsonUtil;
import cn.hanyuweb.util.RedisPoolUtil;
import redis.clients.jedis.Jedis;
@Controller
public class SearchController {
	
	@Autowired
	AttrServiceInf attrServiceInf;
	@Autowired
	SearchServiceInf searchServiceInf;
	
	@RequestMapping("do_search_solr")
	public String do_search_solr(String search,ModelMap map){
		List<OBJECT_T_MALL_SKU_KEYWORDS> sku_list=null;
		try {
			String json = MyHttpGetUtil.doGet(LoadPropertyUtil.load("solr.properties", "solr_path")+"/search_param="+search+".do");
			sku_list = MyJsonUtil.json_to_list(json, OBJECT_T_MALL_SKU_KEYWORDS.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("sku_list", sku_list);
		map.put("search", search);
		return "sale_search_solr_sku_list";
	}
	@RequestMapping("goto_search_detail")
	public String goto_search_detail(int sku_id,int spu_id,ModelMap map){
		//获取sku详情信息对象
		OBJECT_T_MALL_SKU_DETAIL sku_detail=searchServiceInf.get_sku_detail(sku_id);
		//获取sku信息集合
		List<T_MALL_SKU> sku_list=searchServiceInf.get_sku_list_by_spu_id(spu_id);
		map.put("obj_sku", sku_detail);
		map.put("list_sku", sku_list);
		return "sale_search_detail";
	}
	@RequestMapping("do_search_by_attr")
	public String do_search_by_attr(int class_2_id,MODEL_T_SKU_ATTR_VALUE list_attr,ModelMap map){
		List<T_MALL_SKU_ATTR_VALUE> sku_value_list = list_attr.getSku_value_list();
		List<OBJECT_T_MALL_SKU> sku_list=new ArrayList<OBJECT_T_MALL_SKU>();
		if(sku_value_list==null){
			//如果属性参数集合为空，说明没有属性过滤条件，显示所有的二级列表下的sku
			String key="class_2_"+class_2_id;
			sku_list = MyCacheUtil.get_list(key,OBJECT_T_MALL_SKU.class);
			if(sku_list==null || sku_list.size()==0){
				sku_list=searchServiceInf.get_sku_list_by_class_2_id(class_2_id);
				MyCacheUtil.save_list(key,sku_list);
			}
		}else if(sku_value_list.size()==1){
			//如果属性集合的长度为1,说明按照一种条件检索，直接取redis中初始化的属性条件检索集合
			int shxm_id = sku_value_list.get(0).getShxm_id();
			int shxzh_id = sku_value_list.get(0).getShxzh_id();
			String key="attr_"+class_2_id+"_"+shxm_id+"_"+shxzh_id;
			sku_list = MyCacheUtil.get_list(key, OBJECT_T_MALL_SKU.class);
			if(sku_list==null || sku_list.size()==0){
				initCache(class_2_id);
			}
		} else{
			//如果属性条件的长度大于1，说明是符合条件检索
			//1.获取所有key的集合
			Jedis jedis = RedisPoolUtil.getJedis();
			String[] keys = new String[sku_value_list.size()];
			String dstkey="dst";
			for (int i=0;i<sku_value_list.size();i++) {
				T_MALL_SKU_ATTR_VALUE sku = sku_value_list.get(i);
				String key="attr_"+class_2_id+"_"+sku.getShxm_id()+"_"+sku.getShxzh_id();
				keys[i]=key;
				dstkey=dstkey+"_"+key;
			}
			Boolean exists = jedis.exists(dstkey);
			System.out.println(exists);
			if(!exists){
				jedis.zinterstore(dstkey, keys);
			}
			sku_list = MyCacheUtil.get_list(dstkey, OBJECT_T_MALL_SKU.class);
//			if(sku_list==null || sku_list.size()==0){
//				//这个判空不应该存在，因为程序到达这里为空有可能是本身数据没有了
//				sku_list=searchServiceInf.get_sku_list_by_attr(class_2_id, sku_value_list);
//			}
		}
		map.put("sku_list", sku_list);
		return "sale_search_sku_lsit";
	}
	@RequestMapping("do_search_by_attr_array")
	public String do_search_by_attr_array(int class_2_id,@RequestParam("attr_list[]") String[] attr_list){
		
		return "sale_search_sku_lsit";
	}

	@RequestMapping("goto_class_search_String")
	public String goto_class_search_String(int class_2_id,String class_2_name,ModelMap map,String search_ids){
		List<OBJECT_T_MALL_ATTR> attr_list = attrServiceInf.get_attr_list_by_class_2_id(class_2_id);
		List<OBJECT_T_MALL_SKU> sku_list=searchServiceInf.get_sku_list_by_class_2_id(class_2_id,search_ids);
		map.put("class_2_id", class_2_id);
		map.put("class_2_name", class_2_name);
		map.put("sku_list", sku_list);
		map.put("attr_list", attr_list);
		return "sale_search";
	}
	@RequestMapping("goto_class_search")
	public String goto_class_search(int class_2_id,String class_2_name,ModelMap map){
		List<OBJECT_T_MALL_ATTR> attr_list = attrServiceInf.get_attr_list_by_class_2_id(class_2_id);
		String key="class_2_"+class_2_id;
		List<OBJECT_T_MALL_SKU> sku_list = MyCacheUtil.get_list(key,OBJECT_T_MALL_SKU.class);
		if(sku_list==null || sku_list.size()==0){
			sku_list=searchServiceInf.get_sku_list_by_class_2_id(class_2_id);
			MyCacheUtil.save_list(key,sku_list);
		}
		map.put("class_2_id", class_2_id);
		map.put("class_2_name", class_2_name);
		map.put("sku_list", sku_list);
		map.put("attr_list", attr_list);
		return "sale_search";
	}
	@RequestMapping("initCache")
	public void initCache(int class_2_id){
		List<OBJECT_T_MALL_ATTR> attr_list = attrServiceInf.get_attr_list_by_class_2_id(class_2_id);
		//遍历获得的attr_list集合获取所有的attr_id
		for (int i = 0; i < attr_list.size(); i++) {
			OBJECT_T_MALL_ATTR attr = attr_list.get(i);
			int attr_id = attr.getId();
			List<T_MALL_VALUE> value_list = attr.getAttr_value();
			//遍历获得的value_list集合获取所有的value_id
			for (int j = 0; j < value_list.size(); j++) {
				T_MALL_VALUE value = value_list.get(j);
				int value_id = value.getId();
				String key="attr_"+class_2_id+"_"+attr_id+"_"+value_id;
				List<T_MALL_SKU_ATTR_VALUE> av_list=new ArrayList<T_MALL_SKU_ATTR_VALUE>();
				T_MALL_SKU_ATTR_VALUE av= new T_MALL_SKU_ATTR_VALUE();
				av.setShxm_id(attr_id);
				av.setShxzh_id(value_id);
				av_list.add(av);
				List<OBJECT_T_MALL_SKU> get_sku_list_by_attr = searchServiceInf.get_sku_list_by_attr(class_2_id,av_list );
				MyCacheUtil.save_list(key, get_sku_list_by_attr);
			}
		}
	}
}
