package cn.hanyuweb.bean;

import java.util.List;

public class OBJECT_T_MALL_FLOW extends T_MALL_FLOW {
	private List<T_MALL_ORDER_INFO> order_info_list;

	public List<T_MALL_ORDER_INFO> getOrder_info_list() {
		return order_info_list;
	}

	public void setOrder_info_list(List<T_MALL_ORDER_INFO> order_info_list) {
		this.order_info_list = order_info_list;
	}
	
}
