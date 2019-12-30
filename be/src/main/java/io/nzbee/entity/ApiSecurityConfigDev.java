package io.nzbee.entity;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Profile("dev")
@Order(1)
public class ApiSecurityConfigDev extends WebSecurityConfigurerAdapter  {

	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        web
	           .ignoring()
	               .antMatchers("/**");
	    }
}
