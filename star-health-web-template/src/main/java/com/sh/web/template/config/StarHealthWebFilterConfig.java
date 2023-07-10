package com.sh.web.template.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sh.web.template.filters.StarHealthWebFilter;
@SpringBootConfiguration
public class StarHealthWebFilterConfig implements WebMvcConfigurer {
	@Autowired
	private StarHealthWebFilter starHealthWebFilter;
		
	@Bean
	public FilterRegistrationBean<StarHealthWebFilter> filters() {
		FilterRegistrationBean<StarHealthWebFilter> filter = new FilterRegistrationBean<>();
		filter.setFilter(starHealthWebFilter);
		filter.addUrlPatterns("/*");
	  return filter;
	}
}
