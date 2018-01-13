package cn.hanyuweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hanyuweb.bean.OBJECT_T_MALL_SKU;
import cn.hanyuweb.bean.OBJECT_T_MALL_SKU_DETAIL;
import cn.hanyuweb.bean.T_MALL_SKU;
import cn.hanyuweb.bean.T_MALL_SKU_ATTR_VALUE;
import cn.hanyuweb.mapper.SearchMapper;
@Service
public class SearchServiceImp implements SearchServiceInf {
	@Autowired
	SearchMapper searchMapper;
	@Override
	public List<OBJECT_T_MALL_SKU> get_sku_list_by_class_2_id(int class_2_id) {
		List<OBJECT_T_MALL_SKU> sku_list=searchMapper.select_sku_list_by_class_2_id(class_2_id);
		return sku_list;
	}
	@Override
	public List<OBJECT_T_MALL_SKU> get_sku_list_by_class_2_id(int class_2_id, String search_ids) {
		List<OBJECT_T_MALL_SKU> sku_list=null;
		if(search_ids!=null){
			List<T_MALL_SKU_ATTR_VALUE> av_ids_list= new ArrayList<T_MALL_SKU_ATTR_VALUE>();
			String[] ids = search_ids.split("and");
			for (String string : ids) {
				String[] split = string.split("_");
				T_MALL_SKU_ATTR_VALUE t_MALL_SKU_ATTR_VALUE = new T_MALL_SKU_ATTR_VALUE();
				t_MALL_SKU_ATTR_VALUE.setShxm_id(Integer.parseInt(split[0]));
				t_MALL_SKU_ATTR_VALUE.setShxzh_id(Integer.parseInt(split[1]));
				av_ids_list.add(t_MALL_SKU_ATTR_VALUE);
			}
			
			sku_list=searchMapper.select_sku_list_by_class_2_id_and_attr(class_2_id,av_ids_list);
		}else{
			sku_list=searchMapper.select_sku_list_by_class_2_id(class_2_id);
		}
		return sku_list;
	}
	@Override
	public List<OBJECT_T_MALL_SKU> get_sku_list_by_attr(int class_2_id, List<T_MALL_SKU_ATTR_VALUE> sku_value_list) {
		List<OBJECT_T_MALL_SKU> sku_list=null;
		if(sku_value_list==null){
			sku_list=searchMapper.select_sku_list_by_class_2_id(class_2_id);
		}else{
			sku_list=searchMapper.select_sku_list_by_class_2_id_and_attr(class_2_id,sku_value_list);
		}
		return sku_list;
	}
	@Override
	public OBJECT_T_MALL_SKU_DETAIL get_sku_detail(int sku_id) {
		OBJECT_T_MALL_SKU_DETAIL sku_detail=searchMapper.select_sku_detail(sku_id);
		return sku_detail;
	}
	@Override
	public List<T_MALL_SKU> get_sku_list_by_spu_id(int spu_id) {
		List<T_MALL_SKU> sku_list=searchMapper.select_sku_list_by_spu_id(spu_id);
		return sku_list;
	}

}
