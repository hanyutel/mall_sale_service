package cn.hanyuweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hanyuweb.bean.T_MALL_SHOPPINGCAR;
import cn.hanyuweb.bean.T_MALL_USER_ACCOUNT;
import cn.hanyuweb.mapper.ShoppingCartMapper;
@Service
public class ShoppingCartServiceImp implements ShoppingCartServiceInf {

	@Autowired
	ShoppingCartMapper shoppingCartMapper;

	@Override
	public List<T_MALL_SHOPPINGCAR> get_cart_list_by_user_id(T_MALL_USER_ACCOUNT user) {
		 List<T_MALL_SHOPPINGCAR> cart_list_db=shoppingCartMapper.select_cart_list_by_user_id(user);
		return cart_list_db;
	}

	@Override
	public void save_cart_list(List<T_MALL_SHOPPINGCAR> cart_list) {
		shoppingCartMapper.insert_cart_list(cart_list);
	}

	@Override
	public void save_cart(T_MALL_SHOPPINGCAR cart) {
		shoppingCartMapper.insert_cart(cart);
	}

	@Override
	public void update_cart(T_MALL_SHOPPINGCAR cart) {
		shoppingCartMapper.update_cart(cart);
	}
}