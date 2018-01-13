package cn.hanyuweb.service;

import cn.hanyuweb.bean.OBJECT_T_MALL_ORDER;
import cn.hanyuweb.bean.T_MALL_ADDRESS;
import cn.hanyuweb.exception.OverSaleException;

public interface OrderServiceInf {

	OBJECT_T_MALL_ORDER save_order(OBJECT_T_MALL_ORDER order, T_MALL_ADDRESS get_address);

	void pay_order(OBJECT_T_MALL_ORDER order) throws OverSaleException;

}
