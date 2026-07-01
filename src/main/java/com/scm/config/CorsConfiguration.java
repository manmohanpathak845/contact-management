package com.scm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {
	
	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		
		return new WebMvcConfigurer() {
			
			public void addCorsMappings(CorsRegistry corsRegistry) {
				
				corsRegistry.addMapping("/**")
					.allowedOriginPatterns("*")
					.allowedMethods("GET","POST","PUT","DELETE")
					.allowedHeaders("*")
					.allowCredentials(true);
				
			}
			
		};
	}
	
}
