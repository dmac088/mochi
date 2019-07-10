package io.nzbee.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan({
	"io.nzbee.domain",
	"io.nzbee.security",
	"io.nzbee.services",
	"io.nzbee.dao",
	"io.nzbee.entity"})
public class UnitTestConfig {

	@Bean 
    public RestTemplate unitTestTemplate() {
		RestTemplate template = new RestTemplate();
		return template;
    }

}
