package io.javabrains.springbootstarter.test;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class UnitTestConfig {

	@Bean 
    public RestTemplate unitTestTemplate() {
		RestTemplate template = new RestTemplate();
		return template;
    }
	
}
