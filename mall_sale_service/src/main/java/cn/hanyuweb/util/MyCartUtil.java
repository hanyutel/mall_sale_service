package cn.hanyuweb.util;

import java.math.BigDecimal;
import java.util.List;

import cn.hanyuweb.bean.T_MALL_SHOPPINGCAR;

public class MyCartUtil {

	public static BigDecimal get_cart_total_price(List<T_MALL_SHOPPINGCAR> cart_list) {
		BigDecimal total_price=new BigDecimal("0");
		if(cart_list!=null && cart_list.size()>0){
			for (T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR : cart_list) {
				if(t_MALL_SHOPPINGCAR.getShfxz().equals("1")){
					total_price=total_price.add(new BigDecimal(t_MALL_SHOPPINGCAR.getHj()+""));
				}
			}
		}
		return total_price;
	}
	public static  int get_cart_total_number(List<T_MALL_SHOPPINGCAR> cart_list) {
		int total_number=0;
		if(cart_list!=null && cart_list.size()>0){
			for (T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR : cart_list) {
				if(t_MALL_SHOPPINGCAR.getShfxz().equals("1")){
					total_number+=t_MALL_SHOPPINGCAR.getTjshl();
				}
			}
		}
		return total_number;
	}
}
