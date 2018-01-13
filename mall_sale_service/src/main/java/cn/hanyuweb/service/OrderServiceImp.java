package cn.hanyuweb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hanyuweb.bean.OBJECT_T_MALL_FLOW;
import cn.hanyuweb.bean.OBJECT_T_MALL_ORDER;
import cn.hanyuweb.bean.T_MALL_ADDRESS;
import cn.hanyuweb.bean.T_MALL_ORDER_INFO;
import cn.hanyuweb.exception.OverSaleException;
import cn.hanyuweb.mapper.OrderMapper;
import cn.hanyuweb.util.MyTimeUtil;

@Service
public class OrderServiceImp implements OrderServiceInf {
	@Autowired
	OrderMapper orderMapper;

	@Override
	public OBJECT_T_MALL_ORDER save_order(OBJECT_T_MALL_ORDER order, T_MALL_ADDRESS address) {
		// 保存order，返回主键
		order.setDzh_id(address.getId());
		order.setDzh_mch(address.getDz_mch());
		order.setJdh(0);
		order.setShhr(address.getShjr());
		orderMapper.insert_order(order);
		// 遍历保存物流信息
		List<OBJECT_T_MALL_FLOW> flow_list = order.getFlow_list();
		for (OBJECT_T_MALL_FLOW flow : flow_list) {
			flow.setDd_id(order.getId());
			flow.setMdd(address.getDz_mch());
			orderMapper.insert_flow(flow);
			List<T_MALL_ORDER_INFO> order_info_list = flow.getOrder_info_list();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("order_info_list", order_info_list);
			paramMap.put("order_id", order.getId());
			paramMap.put("flow_id", flow.getId());
			// 保存订单sku信息
			orderMapper.insert_info_list(paramMap);
			// 删除购物车中的相应商品
			orderMapper.delete_cart_by_checked(order_info_list);

		}
		return order;

	}

	@Override
	public void pay_order(OBJECT_T_MALL_ORDER order) throws OverSaleException {
		order.setJdh(1);
		order.setYjsdshj(MyTimeUtil.getTime(3));
		// 修改订单状态
		orderMapper.update_order_status(order);
		// 减小库存
		List<OBJECT_T_MALL_FLOW> flow_list = order.getFlow_list();
		for (OBJECT_T_MALL_FLOW flow : flow_list) {
			List<T_MALL_ORDER_INFO> order_info_list = flow.getOrder_info_list();
			// 遍历所有的info进行操作
			for (T_MALL_ORDER_INFO info : order_info_list) {
				int kc = orderMapper.select_kc(info.getSku_id());
				// 如果库存足够，减少库存，否则抛出异常，回滚事务
				if (kc >= info.getSku_shl()) {
					orderMapper.update_kc(info.getSku_id());
				} else {
					throw new OverSaleException("库存不足");
				}
			}
			flow.setLxfsh("18346073561");
			flow.setPsmsh("商品飞奔中");
			flow.setYwy("宇哥");
			flow.setPsshj(MyTimeUtil.getTime(1));
			orderMapper.update_flow(flow);
		}
	}

}
