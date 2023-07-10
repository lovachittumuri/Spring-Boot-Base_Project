package com.sh.web.template.soap.stub;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Scope("singleton")
public class StarHealthStubFactory<T> {
	public T getApacheCxfBasedSoapStub(String url, Class<T> stubType) {
		T stub = null;
		try {
			JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
			jaxWsProxyFactoryBean.setServiceClass(stubType);
			jaxWsProxyFactoryBean.setAddress(url);
			stub = jaxWsProxyFactoryBean.create(stubType);
			System.out.println("SOAP Proxy has been created");
		} catch (Exception exp) {
			System.out.println("Exception occured while creation of SOAP Proxy");
			System.out.println("Error Message:" + exp.getMessage());
		}
		return stub;
	}
}