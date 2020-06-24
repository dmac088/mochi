package io.nzbee.resources.token;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import io.nzbee.resources.controllers.TokenController;
import io.nzbee.security.CustomTokenEnhancer;

public class TokenResourceAssembler extends RepresentationModelAssemblerSupport<CustomTokenEnhancer, TokenResource> {

	public TokenResourceAssembler() {
		super(TokenController.class, TokenResource.class);
	}

	@Override
	public TokenResource toModel(CustomTokenEnhancer entity) {
		TokenResource tr = new TokenResource(entity); 
		return tr;
	}

}
