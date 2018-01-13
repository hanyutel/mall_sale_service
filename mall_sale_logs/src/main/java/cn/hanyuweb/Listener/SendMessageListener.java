package cn.hanyuweb.Listener;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class SendMessageListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		MapMessage mapMessage=(MapMessage) message;
		try {
			Integer orderid =  (Integer) mapMessage.getObject("orderid");
			double zhfje = mapMessage.getDouble("zhfje");
//			sendMessage("18346073561","购买成功，您的订单号为"+orderid+"支付金额为"+zhfje);
			sendMessage("18346073561",orderid+"",zhfje+"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void sendMessage(String phonenum,String orderid,String money)  throws Exception{
		CloseableHttpClient build = HttpClientBuilder.create().build();
		//获取请求
		HttpPost post = new HttpPost("http://localhost:8888/SendMessage/message");
		
		//准备请求参数
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair pair = new BasicNameValuePair("phonenum",phonenum);
		NameValuePair pair2 = new BasicNameValuePair("msg",orderid);
		NameValuePair pair3 = new BasicNameValuePair("money",money);
		list.add(pair);
		list.add(pair2);
		list.add(pair3);
		HttpEntity httpEntity = new UrlEncodedFormEntity(list,"utf-8");
		//设置请求参数
		post.setEntity(httpEntity);
		CloseableHttpResponse response = build.execute(post);
		HttpEntity entity = response.getEntity();
		if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK) {
			String string = EntityUtils.toString(entity, "utf-8");
			System.out.println(string);
		}else {
			String string = EntityUtils.toString(entity, "utf-8");
			System.out.println(string);
		}
		EntityUtils.consume(entity);
		build.close();
	}

}
