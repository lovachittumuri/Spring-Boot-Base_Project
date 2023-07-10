package com.sh.web.template.config;

import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sh.web.template.context.WebContext;
@SpringBootConfiguration
public class WebContextConfig implements WebMvcConfigurer{
	@Autowired
	private WebContext webContext;
	
	@Bean
	public ServletListenerRegistrationBean<ServletContextListener> listenerRegistrationBean() {
		ServletListenerRegistrationBean<ServletContextListener> bean = new ServletListenerRegistrationBean<>();
		bean.setListener(webContext);
	  return bean;
	}
}
