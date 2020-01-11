package io.nzbee.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
@ComponentScan({
	"io.nzbee.*"})
public class UT_Config {

	@Bean 
    public RestTemplate unitTestTemplate() {
		RestTemplate template = new RestTemplate();
		return template;
    }

}
