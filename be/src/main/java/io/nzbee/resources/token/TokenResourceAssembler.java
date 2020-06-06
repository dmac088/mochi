package io.nzbee.resources.token;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import io.nzbee.security.CustomTokenEnhancer;

public class TokenResourceAssembler extends ResourceAssemblerSupport<CustomTokenEnhancer, TokenResource> {

	public TokenResourceAssembler(Class<?> controllerClass, Class<TokenResource> resourceType) {
		super(controllerClass, resourceType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TokenResource toResource(CustomTokenEnhancer entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
