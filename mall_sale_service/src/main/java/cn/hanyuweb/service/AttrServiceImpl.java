package cn.hanyuweb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hanyuweb.bean.OBJECT_T_MALL_ATTR;
import cn.hanyuweb.mapper.AttrMapper;
@Service
public class AttrServiceImpl implements AttrServiceInf {
	
	@Autowired
	private AttrMapper attrMapper;

	@Override
	public List<OBJECT_T_MALL_ATTR> get_attr_list_by_class_2_id(int class_2_id) {
		List<OBJECT_T_MALL_ATTR>  list=attrMapper.select_attr_list_by_class_2_id(class_2_id);
		return list;
	}

	@Override
	public void save_attr(List<OBJECT_T_MALL_ATTR> attr_value, int class_2_id) {
		for (OBJECT_T_MALL_ATTR object_T_MALL_ATTR : attr_value) {
			object_T_MALL_ATTR.setFlbh2(class_2_id);
//			attrMapper.insert_attr(object_T_MALL_ATTR);
//			attrMapper.insert_attr_param(class_2_id,object_T_MALL_ATTR);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("class_2_id", class_2_id);
			paramMap.put("attr", object_T_MALL_ATTR);
			attrMapper.insert_attr_map(paramMap);
			attrMapper.insert_value(object_T_MALL_ATTR.getId(),object_T_MALL_ATTR.getAttr_value());
		}
		
	}


}
