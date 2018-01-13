package cn.hanyuweb.server;

import java.util.List;

import javax.jws.WebService;

import cn.hanyuweb.bean.T_MALL_ADDRESS;
@WebService
public interface AddressServerInf {
	
	void save_address(T_MALL_ADDRESS address);
	int delete_address_by_user_id(int user_id);
	int delete_address(int id);
	int update_address(T_MALL_ADDRESS address);
	List<T_MALL_ADDRESS> get_address_list(int user_id);
	T_MALL_ADDRESS get_address(int id);
}
