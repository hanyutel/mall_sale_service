package cn.hanyuweb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.hanyuweb.bean.OBJECT_T_MALL_ATTR;
import cn.hanyuweb.bean.T_MALL_ATTR;
import cn.hanyuweb.bean.T_MALL_VALUE;

public interface AttrMapper {

	List<OBJECT_T_MALL_ATTR> select_attr_list_by_class_2_id(int class_2_id);

	void insert_attr(T_MALL_ATTR t_MALL_ATTR);

	void insert_value(OBJECT_T_MALL_ATTR object_T_MALL_ATTR);

	void insert_value(@Param("shxm_id") int id, @Param("attr_value") List<T_MALL_VALUE> attr_value);

	void insert_attr_param(@Param("class_2_id") int class_2_id, @Param("attr") OBJECT_T_MALL_ATTR object_T_MALL_ATTR);

	void insert_attr_map(Map<String, Object> paramMap);

}
