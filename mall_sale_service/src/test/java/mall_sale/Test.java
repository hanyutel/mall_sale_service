package mall_sale;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import cn.hanyuweb.server.TestServer;
import cn.hanyuweb.server.TestServerService;

public class Test {

	public static void main(String[] args) {
		String a="";
		try {
			a=URLDecoder.decode("https://list.jd.com/list.html?cat=670,671,1105&ev=5283_87720&sort=sort_totalsales15_desc&trans=1&JL=3_%E6%98%BE%E5%8D%A1%E5%9E%8B%E5%8F%B7_GTX1060#J_crumbsBarhttps:/list.jd.com/list.html?cat=670,671,1105&ev=5283_87720%40244_73390&sort=sort_totalsales15_desc&trans=1&JL=3_%E5%B1%8F%E5%B9%95%E5%B0%BA%E5%AF%B8_14.0%E8%8B%B1%E5%AF%B8#J_crumbsBar","UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(a);
		TestServerService serverInfService = new TestServerService();
		TestServer port = serverInfService.getTestServerPort();
		System.out.println(port.sayHello("韩宇"));
		
	}
}
