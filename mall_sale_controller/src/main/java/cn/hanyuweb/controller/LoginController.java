package cn.hanyuweb.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.terracotta.statistics.Time;

import cn.hanyuweb.bean.T_MALL_SHOPPINGCAR;
import cn.hanyuweb.bean.T_MALL_USER_ACCOUNT;
import cn.hanyuweb.server.UserServerInf;
import cn.hanyuweb.service.ShoppingCartServiceInf;
import cn.hanyuweb.util.MyJsonUtil;
	
@Controller
public class LoginController {
	@Autowired
	ShoppingCartServiceInf shoppingCartServiceInf;
	@Autowired
	UserServerInf userServerInf;
	@Autowired
	JmsTemplate jmsTemplate;
	@Autowired
	ActiveMQQueue userQueueDestination;
	
	@RequestMapping("/goto_login")
	public String goto_login(){
		return "sale_login";
	}
	@RequestMapping("/do_logout")
	public String do_logout(HttpSession session){
		session.invalidate();
		return "redirect:/goto_login.do";
	}
	@RequestMapping("/login")
	public String login(String dataSource_type,@CookieValue(value="cart_list_cookie",required=false) String cart_list_cookie,HttpSession session,T_MALL_USER_ACCOUNT account,HttpServletResponse response){
		//校验用户登录信息
		T_MALL_USER_ACCOUNT login_user=null;
		try {
			if(dataSource_type.equals("d1")){
				login_user = userServerInf.login(account);
			}else if(dataSource_type.equals("d2")){
				login_user = userServerInf.login_dev(account);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return "redirect:/goto_login.do";
		}
		if(login_user== null){
			return "redirect:/goto_login.do";
		}else{
			List<T_MALL_SHOPPINGCAR> cart_list=shoppingCartServiceInf.get_cart_list_by_user_id(login_user);
			List<T_MALL_SHOPPINGCAR> cart_list_cookie_cookie = MyJsonUtil.json_to_list(cart_list_cookie, T_MALL_SHOPPINGCAR.class);
//			List<T_MALL_SHOPPINGCAR> cart_list_session = (List<T_MALL_SHOPPINGCAR>)session.getAttribute("cart_list_session");
			if(cart_list==null || cart_list.size()==0){
				//db中没有购物车
				
				if(cart_list_cookie_cookie!=null && cart_list_cookie_cookie.size()>0){
					cart_list=new ArrayList<T_MALL_SHOPPINGCAR>();
					//cookie中有商品
					for (T_MALL_SHOPPINGCAR cart : cart_list_cookie_cookie) {
						cart.setYh_id(login_user.getId());
						cart_list.add(cart);
					}
					shoppingCartServiceInf.save_cart_list(cart_list);
				}
			}else{
				//db中有购物车
				if(cart_list_cookie_cookie!=null && cart_list_cookie_cookie.size()>0){
					for (T_MALL_SHOPPINGCAR cart : cart_list_cookie_cookie) {
						cart.setYh_id(login_user.getId());
						boolean f = cart_isexist(cart,cart_list);
						if(!f){
							//cookie中的商品db中存在
							for (T_MALL_SHOPPINGCAR cart_db : cart_list) {
								if(cart_db.getSku_id()==cart.getSku_id()){
									cart_db.setTjshl(cart_db.getTjshl()+cart.getTjshl());
									cart_db.setHj(cart_db.getTjshl()*cart_db.getSku_jg());
									shoppingCartServiceInf.update_cart(cart_db);
								}
							}
						}else{
							//cookie中的商品db中不存在
							cart_list.add(cart);
							shoppingCartServiceInf.save_cart(cart);
						}
					}
				}
			}
			session.setAttribute("cart_list_session", cart_list);
			session.setAttribute("user", login_user);
			//登录成功，将用户个性化信息放入cookie中
			Cookie cookie=null;
			try {
				cookie = new Cookie("yh_nch",URLEncoder.encode(login_user.getYh_nch(),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Cookie cookie2 = new Cookie("cart_list_cookie","");
			cookie.setMaxAge(60*60*24);
			cookie2.setMaxAge(60*60*24*7);
			response.addCookie(cookie);
			response.addCookie(cookie2); 
			final T_MALL_USER_ACCOUNT user=login_user;
			jmsTemplate.send(userQueueDestination,new MessageCreator() {
				
				@Override
				public Message createMessage(Session session) throws JMSException {
					TextMessage textMessage = session.createTextMessage("用户"+user.getYh_nch()+"于"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"登录成功");
					return textMessage;
				}
			});
			
			
			return "redirect:/index.do";
		}
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
}
