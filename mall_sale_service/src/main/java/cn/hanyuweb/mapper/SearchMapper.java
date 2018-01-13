package cn.hanyuweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.hanyuweb.bean.OBJECT_T_MALL_SKU;
import cn.hanyuweb.bean.OBJECT_T_MALL_SKU_DETAIL;
import cn.hanyuweb.bean.T_MALL_SKU;
import cn.hanyuweb.bean.T_MALL_SKU_ATTR_VALUE;

public interface SearchMapper {

	List<OBJECT_T_MALL_SKU> select_sku_list_by_class_2_id(int class_2_id);

	List<OBJECT_T_MALL_SKU> select_sku_list_by_class_2_id_and_attr(@Param("class_2_id") int class_2_id,
			@Param("av_ids_list") List<T_MALL_SKU_ATTR_VALUE> av_ids_list);

	List<T_MALL_SKU> select_sku_list_by_spu_id(int spu_id);

	OBJECT_T_MALL_SKU_DETAIL select_sku_detail(int sku_id);

}
