package cn.hanyuweb.server;

import javax.jws.WebService;

import cn.hanyuweb.bean.T_MALL_USER_ACCOUNT;
@WebService
public interface UserServerInf {
	public T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user);
	public T_MALL_USER_ACCOUNT check_yh_mch(String yh_mch);
	public T_MALL_USER_ACCOUNT regist(T_MALL_USER_ACCOUNT user);
	public T_MALL_USER_ACCOUNT login_dev(T_MALL_USER_ACCOUNT user); 
}
