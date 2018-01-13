package cn.hanyuweb.Listener;

import java.math.BigDecimal;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.beans.factory.annotation.Autowired;

import cn.hanyuweb.bean.T_MALL_ORDER_LOG;
import cn.hanyuweb.service.OrderLogServiceInf;

public class OrderMessageListener implements MessageListener {

	@Autowired
	OrderLogServiceInf orderLogServiceInf;
	@Override
	public void onMessage(Message message) {
		MapMessage mapMessage =(MapMessage) message; 
		try {
			String orderid = mapMessage.getString("orderid");
			int zhfpt_id = mapMessage.getInt("zhfpt_id");
			String shkzhh = mapMessage.getString("shkzhh");
			String zhfzhh = mapMessage.getString("zhfzhh");
			double zhfje = mapMessage.getDouble("zhfje");
			T_MALL_ORDER_LOG log = new T_MALL_ORDER_LOG();
			log.setDd_id(Integer.parseInt(orderid));
			log.setShkzhh(shkzhh);
			log.setZhfje(new BigDecimal(zhfje+""));
			log.setZhfpt_id(zhfpt_id);
			log.setZhfzhh(zhfzhh);
//			Thread.sleep(30*1000);
			orderLogServiceInf.save_order_log(log);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
