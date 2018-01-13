package cn.hanyuweb.mapper;

import java.util.List;
import java.util.Map;

import cn.hanyuweb.bean.OBJECT_T_MALL_FLOW;
import cn.hanyuweb.bean.OBJECT_T_MALL_ORDER;
import cn.hanyuweb.bean.T_MALL_ORDER_INFO;

public interface OrderMapper {

	void insert_order(OBJECT_T_MALL_ORDER order);

	void insert_flow(OBJECT_T_MALL_FLOW flow);

	void insert_info_list(Map<String, Object> paramMap);

	void delete_cart_by_checked(List<T_MALL_ORDER_INFO> order_info_list);

	void update_order_status(OBJECT_T_MALL_ORDER order);

	int select_kc(int sku_id);
	
	int select_kc_for_update(int sku_id);
	
	void update_kc(int i);

	void update_flow(OBJECT_T_MALL_FLOW flow);

}
