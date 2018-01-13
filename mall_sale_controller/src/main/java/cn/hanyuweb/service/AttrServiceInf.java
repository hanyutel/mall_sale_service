package cn.hanyuweb.service;

import java.util.List;

import cn.hanyuweb.bean.OBJECT_T_MALL_ATTR;
import cn.hanyuweb.bean.T_MALL_ATTR;

public interface AttrServiceInf {

	List<OBJECT_T_MALL_ATTR> get_attr_list_by_class_2_id(int class_2_id);

	void save_attr(List<OBJECT_T_MALL_ATTR> list, int class_2_id);

}
