package io.nzbee.resources.search;

import org.springframework.hateoas.ResourceSupport;

import io.nzbee.domain.product.Product;
import io.nzbee.resource.controllers.SearchController;
import io.nzbee.ui.component.web.facet.FacetContainer;
import lombok.Getter;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;

@Getter
public class SearchResource extends ResourceSupport {

	@Autowired
	private PagedResourcesAssembler<Product> parAssembler;
	
	public SearchResource(String locale, 
						  String currency, 
						  String category,
						  String term,
						  int page,
						  int size,
						  String sortBy,
						  FacetContainer searchFacets) {

		add(linkTo(methodOn(SearchController.class).search(	locale, 
															currency, 
															category, 
															term, 
															page, 
															size, 
															sortBy, 
															searchFacets,
															parAssembler)).withRel("products"));
		
    }
	
}
