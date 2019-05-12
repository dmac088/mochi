package io.nzbee;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryRestMvcConfiguration extends RepositoryRestConfigurerAdapter {

	 @Override
	 public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
	           config.setPageParamName("p")
	                 .setLimitParamName("l")
	                 .setSortParamName("s");
	 }
	
}
