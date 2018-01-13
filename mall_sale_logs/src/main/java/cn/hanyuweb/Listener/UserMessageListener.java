package cn.hanyuweb.Listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import cn.hanyuweb.service.UserServiceInf;

public class UserMessageListener implements MessageListener{

	@Autowired
	UserServiceInf userServiceInf;
	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		String text="";
		try {
			text = textMessage.getText();
			userServiceInf.save_log(text);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		System.out.println(text);
	}

}
