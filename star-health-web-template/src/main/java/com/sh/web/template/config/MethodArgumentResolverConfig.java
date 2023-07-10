package com.sh.web.template.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sh.web.template.method.argument.resolver.RequestContextArgumentResolver;

@Configuration
public class MethodArgumentResolverConfig implements WebMvcConfigurer {
	@Autowired
	private RequestContextArgumentResolver requestContextArgumentResolver; 
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(requestContextArgumentResolver);
		System.out.println("RequestContextArgumentResolver was configured.");
	}
}
