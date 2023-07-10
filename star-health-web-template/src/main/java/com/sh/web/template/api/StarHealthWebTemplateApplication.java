package com.sh.web.template.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.sh.web.template")
public class StarHealthWebTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarHealthWebTemplateApplication.class, args);
	}
}