package io.nzbee.resources.token;

import org.springframework.hateoas.ResourceSupport;

import io.nzbee.security.CustomTokenEnhancer;

public class TokenResource  extends ResourceSupport {

	private final CustomTokenEnhancer token;
	
	public TokenResource(final CustomTokenEnhancer token) {
		this.token = token;
		
	}
	
	public CustomTokenEnhancer getToken() {
		return token;
	}
	
}
