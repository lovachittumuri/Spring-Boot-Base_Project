package com.sh.web.template.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sh.web.template.interceptors.StarHealthWebInterceptor;
@SpringBootConfiguration
public class StarHealthWebInterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private StarHealthWebInterceptor starHealthWebInterceptor;
	
	private String[] excludePatterns= {"/actuator/info","/actuator/health"};
	private String[] addPathPatterns= {"/**"};
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(starHealthWebInterceptor)
		.addPathPatterns(addPathPatterns)
		.excludePathPatterns(excludePatterns);
	}
}
