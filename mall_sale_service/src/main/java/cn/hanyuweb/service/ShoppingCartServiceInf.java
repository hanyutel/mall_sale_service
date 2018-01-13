package cn.hanyuweb.service;

import java.util.List;

import cn.hanyuweb.bean.T_MALL_SHOPPINGCAR;
import cn.hanyuweb.bean.T_MALL_USER_ACCOUNT;

public interface ShoppingCartServiceInf {

	List<T_MALL_SHOPPINGCAR> get_cart_list_by_user_id(T_MALL_USER_ACCOUNT user);

	void save_cart_list(List<T_MALL_SHOPPINGCAR> cart_list);

	void save_cart(T_MALL_SHOPPINGCAR cart);

	void update_cart(T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR);

}
