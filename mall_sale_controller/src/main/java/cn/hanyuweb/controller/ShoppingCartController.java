package cn.hanyuweb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hanyuweb.bean.T_MALL_SHOPPINGCAR;
import cn.hanyuweb.bean.T_MALL_USER_ACCOUNT;
import cn.hanyuweb.service.ShoppingCartServiceInf;
import cn.hanyuweb.util.MyCartUtil;
import cn.hanyuweb.util.MyJsonUtil;
@Controller
public class ShoppingCartController {
	
	@Autowired
	ShoppingCartServiceInf shoppingCartServiceInf;
	@RequestMapping("updata_cart_tjshl")
	public String updata_cart_tjshl(T_MALL_SHOPPINGCAR cart,@CookieValue(value="cart_list_cookie",required=false) String cart_list_cookie,
			HttpSession session,ModelMap map,HttpServletResponse response){
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT)session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> cart_list=new ArrayList<T_MALL_SHOPPINGCAR>();
		if(user==null){
			//没有登录
			cart_list = MyJsonUtil.json_to_list(cart_list_cookie,T_MALL_SHOPPINGCAR.class);
		}else{
			//登录了
			cart_list=(List<T_MALL_SHOPPINGCAR>)session.getAttribute("cart_list_session");
		}
		for (T_MALL_SHOPPINGCAR list_cart : cart_list) {
			if(list_cart.getSku_id()==cart.getSku_id()){
				list_cart.setTjshl(cart.getTjshl());
				list_cart.setHj(list_cart.getTjshl()*list_cart.getSku_jg());
				if(user==null){
					//没有登录
					String json = MyJsonUtil.list_to_json(cart_list);
					Cookie cookie = new Cookie("cart_list_cookie", json);
					cookie.setMaxAge(60*60*24*7);
					response.addCookie(cookie);
				}else{
					//登录了
					shoppingCartServiceInf.update_cart(list_cart);
				}
				break;
			}
		}
		map.put("total_price", MyCartUtil.get_cart_total_price(cart_list));
		map.put("cart_list", cart_list);
		return "sale_cart_inner";
	}

	@RequestMapping("updata_cart_status")
	public String updata_cart_status(T_MALL_SHOPPINGCAR cart,@CookieValue(value="cart_list_cookie",required=false) String cart_list_cookie,
			HttpSession session,ModelMap map,HttpServletResponse response){
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT)session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> cart_list=new ArrayList<T_MALL_SHOPPINGCAR>();
		if(user==null){
			//没有登录
			cart_list = MyJsonUtil.json_to_list(cart_list_cookie,T_MALL_SHOPPINGCAR.class);
		}else{
			//登录了
			cart_list=(List<T_MALL_SHOPPINGCAR>)session.getAttribute("cart_list_session");
		}
		for (T_MALL_SHOPPINGCAR list_cart : cart_list) {
			if(list_cart.getSku_id()==cart.getSku_id()){
				list_cart.setShfxz(cart.getShfxz());
				if(user==null){
					//没有登录
					String json = MyJsonUtil.list_to_json(cart_list);
					Cookie cookie = new Cookie("cart_list_cookie", json);
					cookie.setMaxAge(60*60*24*7);
					response.addCookie(cookie);
				}else{
					//登录了
					shoppingCartServiceInf.update_cart(list_cart);
				}
				break;
			}
		}
		map.put("total_price", MyCartUtil.get_cart_total_price(cart_list));
		map.put("cart_list", cart_list);
		return "sale_cart_inner";
	}
	@RequestMapping("goto_cart")
	public String goto_cart(@CookieValue(value="cart_list_cookie",required=false) String cart_list_cookie,HttpSession session,ModelMap map){
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT)session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> cart_list=new ArrayList<T_MALL_SHOPPINGCAR>();
		if(user==null){
			//没有登录
			cart_list = MyJsonUtil.json_to_list(cart_list_cookie,T_MALL_SHOPPINGCAR.class);
		}else{
			//登录了
			cart_list=(List<T_MALL_SHOPPINGCAR>)session.getAttribute("cart_list_session");
		}
		map.put("total_price", MyCartUtil.get_cart_total_price(cart_list));
		map.put("cart_list", cart_list);
		return "sale_cart";
	}
	@RequestMapping("get_minicart")
	public String get_minicart(@CookieValue(value="cart_list_cookie",required=false) String cart_list_cookie,HttpSession session,ModelMap map){
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT)session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> minicart_list=new ArrayList<T_MALL_SHOPPINGCAR>();
		if(user==null){
			//没有登录
			minicart_list = MyJsonUtil.json_to_list(cart_list_cookie,T_MALL_SHOPPINGCAR.class);
		}else{
			//登录了
			minicart_list=(List<T_MALL_SHOPPINGCAR>)session.getAttribute("cart_list_session");
		}
		map.put("total_price", MyCartUtil.get_cart_total_price(minicart_list));
		map.put("total_number", MyCartUtil.get_cart_total_number(minicart_list));
		map.put("minicart_list", minicart_list);
		return "sale_minicart_inner";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/add_cart")
	public String add_cart(T_MALL_SHOPPINGCAR cart,HttpSession session,HttpServletResponse response,@CookieValue(value="cart_list_cookie",required=false) String cart_list_cookie){
		//获取用户信息
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT)session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> cart_list=new ArrayList<T_MALL_SHOPPINGCAR>();
		//判断用户是否登录
		if(user==null || cart.getYh_id()==0){
			//用户未登录
			//判断cookie是否为空
			if(StringUtils.isBlank(cart_list_cookie)){
				//cookie不存在,添加
				cart_list.add(cart);
			}else{
				//判断cookie中是否已经有该商品
				cart_list = MyJsonUtil.json_to_list(cart_list_cookie,T_MALL_SHOPPINGCAR.class);
				boolean f=cart_isexist(cart,cart_list);
				if(f){
					//cookie中不存在该商品,添加
					cart_list.add(cart);
				}else{
					//cookie中存在该商品,更新
					for (int i = 0; i < cart_list.size(); i++) {
						if(cart_list.get(i).getSku_id()==cart.getSku_id()){
							cart_list.get(i).setTjshl(cart_list.get(i).getTjshl()+cart.getTjshl());
							cart_list.get(i).setHj(cart_list.get(i).getTjshl()*cart_list.get(i).getSku_jg());
							break;
						}
					}
				}
			}
			//更新cookie
			cart_list_cookie = MyJsonUtil.list_to_json(cart_list);
			Cookie cookie =new Cookie("cart_list_cookie", cart_list_cookie);
			cookie.setMaxAge(60*60*24*7);
			response.addCookie(cookie);
		}else{
			//用户已登录
			cart_list = (List<T_MALL_SHOPPINGCAR>)session.getAttribute("cart_list_session");
			if(cart_list ==null || cart_list.size()==0){
				//db中的购物车为空
				cart_list=new ArrayList<T_MALL_SHOPPINGCAR>();
				cart_list.add(cart);
				shoppingCartServiceInf.save_cart(cart);
				session.setAttribute("cart_list_session", cart_list);
			}else{
				boolean f =cart_isexist(cart,cart_list);
				if(f){
					//db中不存在该商品,
					cart_list.add(cart);
					shoppingCartServiceInf.save_cart(cart);
				}else{
					//db中存在该商品,
					for (int i = 0; i < cart_list.size(); i++) {
						if(cart_list.get(i).getSku_id()==cart.getSku_id()){
							cart_list.get(i).setTjshl(cart_list.get(i).getTjshl()+cart.getTjshl());
							cart_list.get(i).setHj(cart_list.get(i).getTjshl()*cart_list.get(i).getSku_jg());
							shoppingCartServiceInf.update_cart(cart_list.get(i));
							break;
						}
					}
				}
			}
		}
		return "redirect:/add_cart_success.do";
	}

	private boolean cart_isexist(T_MALL_SHOPPINGCAR cart, List<T_MALL_SHOPPINGCAR> cart_list) {
		boolean f=true;
		for (int i = 0; i < cart_list.size(); i++) {
			if(cart_list.get(i).getSku_id()==cart.getSku_id()){
				f=false;
				break;
			}
		}
		return f;
	}
	@RequestMapping("add_cart_success")
	public String add_cart_success(){
		return "sale_cart_success";
	}
}
