package io.nzbee.resources.token;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import io.nzbee.resources.controllers.TokenController;
import io.nzbee.security.CustomTokenEnhancer;

public class TokenResourceAssembler extends ResourceAssemblerSupport<CustomTokenEnhancer, TokenResource> {

	public TokenResourceAssembler() {
		super(TokenController.class, TokenResource.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TokenResource toResource(CustomTokenEnhancer entity) {
		TokenResource tr = new TokenResource(entity); 
		return tr;
	}

}
