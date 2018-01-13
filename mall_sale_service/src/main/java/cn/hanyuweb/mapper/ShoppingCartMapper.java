package cn.hanyuweb.mapper;

import java.util.List;

import cn.hanyuweb.bean.T_MALL_SHOPPINGCAR;
import cn.hanyuweb.bean.T_MALL_USER_ACCOUNT;

public interface ShoppingCartMapper {

	List<T_MALL_SHOPPINGCAR> select_cart_list_by_user_id(T_MALL_USER_ACCOUNT user);

	void insert_cart(T_MALL_SHOPPINGCAR cart);

	void insert_cart_list(List<T_MALL_SHOPPINGCAR> cart_list);

	void update_cart(T_MALL_SHOPPINGCAR cart);

}
