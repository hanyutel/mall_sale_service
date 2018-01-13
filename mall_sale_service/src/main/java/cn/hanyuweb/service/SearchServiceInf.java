package cn.hanyuweb.service;

import java.util.List;

import cn.hanyuweb.bean.OBJECT_T_MALL_SKU;
import cn.hanyuweb.bean.OBJECT_T_MALL_SKU_DETAIL;
import cn.hanyuweb.bean.T_MALL_SKU;
import cn.hanyuweb.bean.T_MALL_SKU_ATTR_VALUE;

public interface SearchServiceInf {

	List<OBJECT_T_MALL_SKU> get_sku_list_by_class_2_id(int class_2_id);

	List<OBJECT_T_MALL_SKU> get_sku_list_by_class_2_id(int class_2_id, String search_ids);

	List<OBJECT_T_MALL_SKU> get_sku_list_by_attr(int class_2_id, List<T_MALL_SKU_ATTR_VALUE> sku_value_list);

	OBJECT_T_MALL_SKU_DETAIL get_sku_detail(int sku_id);

	List<T_MALL_SKU> get_sku_list_by_spu_id(int spu_id);

}
