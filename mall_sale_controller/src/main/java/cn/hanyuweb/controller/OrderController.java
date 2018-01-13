package cn.hanyuweb.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.hanyuweb.bean.OBJECT_T_MALL_FLOW;
import cn.hanyuweb.bean.OBJECT_T_MALL_ORDER;
import cn.hanyuweb.bean.T_MALL_ADDRESS;
import cn.hanyuweb.bean.T_MALL_ORDER_INFO;
import cn.hanyuweb.bean.T_MALL_SHOPPINGCAR;
import cn.hanyuweb.bean.T_MALL_USER_ACCOUNT;
import cn.hanyuweb.exception.OverSaleException;
import cn.hanyuweb.server.AddressServerInf;
import cn.hanyuweb.service.OrderServiceInf;
import cn.hanyuweb.service.ShoppingCartServiceInf;
import cn.hanyuweb.util.MyCartUtil;

@Controller
@SessionAttributes("order")
public class OrderController {
	@Autowired
	AddressServerInf addressServerInf;
	@Autowired
	OrderServiceInf orderServiceInf;
	@Autowired
	ShoppingCartServiceInf shoppingCartServiceInf;
	@Autowired
	JmsTemplate jmsTopicTemplate;
	
	@RequestMapping("pay_success")
	public String pay_success(){
		
		return "sale_pay_success";
	}
	@RequestMapping("pay_fail/{msg}")
	public String pay_fail(@PathVariable String msg,ModelMap map){
		map.put("msg", msg);
		return "sale_pay_fail";
	}
	@RequestMapping("goto_pay_order")
	public String goto_pay_order(@ModelAttribute("order") OBJECT_T_MALL_ORDER order,HttpSession session){
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT)session.getAttribute("user");
		try {
			orderServiceInf.pay_order(order);
		} catch (OverSaleException e) {
			return "redirect:/pay_fail/oversale.do";
		}
		
		
		final OBJECT_T_MALL_ORDER final_order=order;
		jmsTopicTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage mapMessage = session.createMapMessage();
				mapMessage.setObject("orderid", final_order.getId());
				mapMessage.setDouble("zhfje", final_order.getZje().doubleValue());
				mapMessage.setString("zhfzhh", "1844444");
				mapMessage.setString("shkzhh", "1755555");
				mapMessage.setInt("zhfpt_id", 55);
				return mapMessage;
			}
		});
		return "redirect:/pay_success.do";
	}
	@RequestMapping("goto_cashier")
	public String goto_cashier(){
		return "sale_cashier";
	}
	@RequestMapping("save_order")
	public String save_order(@ModelAttribute("order") OBJECT_T_MALL_ORDER order,HttpSession session,int address_id,ModelMap map){
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT)session.getAttribute("user");
		order =orderServiceInf.save_order(order,addressServerInf.get_address(address_id));
		map.put("order", order);
		session.setAttribute("cart_list_session",shoppingCartServiceInf.get_cart_list_by_user_id(user) );
		return "redirect:/goto_cashier.do";
	}
	@RequestMapping("goto_check_order")
	public String goto_check_order(HttpSession session,ModelMap map){
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT)session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> cart_list=shoppingCartServiceInf.get_cart_list_by_user_id(user);
		//获取库存地址信息
		Set<String> address_set=new HashSet<String>();
		for (T_MALL_SHOPPINGCAR cart : cart_list) {
			if(cart.getShfxz().equals("1")){
				address_set.add(cart.getKcdz());
			}
		}
		//创建order对象，进行拆单
		OBJECT_T_MALL_ORDER order= new OBJECT_T_MALL_ORDER();
		order.setZje(MyCartUtil.get_cart_total_price(cart_list));
		order.setJdh(0);
		order.setYh_id(user.getId());
		//创建物流信息集合
		List<OBJECT_T_MALL_FLOW> flow_list = new ArrayList<OBJECT_T_MALL_FLOW>();
		Iterator<String> iterator = address_set.iterator();
		while(iterator.hasNext()){
			String kcdz = iterator.next();
			//按库存地址进行物流分配
			OBJECT_T_MALL_FLOW flow = new OBJECT_T_MALL_FLOW();
			flow.setPsfsh("顺丰速递");
			flow.setYh_id(user.getId());
			flow.setMqdd("商品未出库");
			//创建订单信息结合，将统一库存地址的商品放入一个物流包裹中
			List<T_MALL_ORDER_INFO> order_info_list = new ArrayList<T_MALL_ORDER_INFO>();
			for (T_MALL_SHOPPINGCAR cart : cart_list) {
				if(cart.getKcdz().equals(kcdz)&&cart.getShfxz().equals("1")){
					T_MALL_ORDER_INFO info = new T_MALL_ORDER_INFO();
					info.setGwch_id(cart.getId());
					info.setShp_tp(cart.getShp_tp());
					info.setSku_id(cart.getSku_id());
					info.setSku_jg(cart.getSku_jg());
					info.setSku_kcdz(kcdz);
					info.setSku_mch(cart.getSku_mch());
					info.setSku_shl(cart.getTjshl());
					order_info_list.add(info);
				}
			}
			//将商品集合放入物流信息中
			flow.setOrder_info_list(order_info_list);
			//将物流信息放入放入物流信息集合中
			flow_list.add(flow);
		}
		//将物流信息放入到订单中
		order.setFlow_list(flow_list);
		//获取用户地址信息
		List<T_MALL_ADDRESS> address_list = addressServerInf.get_address_list(user.getId());
		map.put("address_list", address_list);
		map.put("order", order);
		return "sale_check_order";
	}
}
