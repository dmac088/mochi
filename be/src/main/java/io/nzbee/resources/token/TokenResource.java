package io.nzbee.resources.token;


import org.springframework.hateoas.RepresentationModel;

import io.nzbee.security.CustomTokenEnhancer;

public class TokenResource  extends RepresentationModel<TokenResource> {

	private final CustomTokenEnhancer token;
	
	public TokenResource(final CustomTokenEnhancer token) {
		this.token = token;
		
	}
	
	public CustomTokenEnhancer getToken() {
		return token;
	}
	
}
