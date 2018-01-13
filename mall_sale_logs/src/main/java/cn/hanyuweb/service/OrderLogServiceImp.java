package cn.hanyuweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hanyuweb.bean.T_MALL_ORDER_LOG;
import cn.hanyuweb.mapper.OrderLogMapper;

@Service
public class OrderLogServiceImp implements OrderLogServiceInf {
	@Autowired
	OrderLogMapper orderLogMapper;

	@Override
	public void save_order_log(T_MALL_ORDER_LOG log) {
		orderLogMapper.insert_order_log(log)	;	
	}
}
