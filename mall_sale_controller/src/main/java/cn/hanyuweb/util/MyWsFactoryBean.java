package cn.hanyuweb.util;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.FactoryBean;

public class MyWsFactoryBean<T> implements FactoryBean<T>{
	private String url;
	private Class<T> t;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Class<T> getT() {
		return t;
	}
	public void setT(Class<T> t) {
		this.t = t;
	}

	public  T getMyWs(String url , Class<T> t){
		JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
		bean.setAddress(url);
		bean.setServiceClass(t);
		T create = (T)bean.create();
		return create;
	}

	@Override
	public T getObject() throws Exception {
		
		return getMyWs(this.url ,this.t);
	}

	@Override
	public Class<?> getObjectType() {
		return t;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
