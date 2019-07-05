package io.nzbee.test;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.nzbee.security.IUserRoleService;
import io.nzbee.security.UserRoleRepository;
import io.nzbee.security.UserRoleService;

public class UnitTestConfig {

	@Bean 
    public RestTemplate unitTestTemplate() {
		RestTemplate template = new RestTemplate();
		return template;
    }
	
	@Bean 
	public IUserRoleService userRoleService()  {
		UserRoleService service = new UserRoleService();
		return service;
	}

}
