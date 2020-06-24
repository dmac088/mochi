package io.nzbee.resources.search;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import io.nzbee.domain.ports.IProductPortService;
import io.nzbee.domain.product.Product;
import io.nzbee.resources.product.ProductResource;
import io.nzbee.search.dto.facet.IFacet;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;


public class SearchResource extends RepresentationModel<SearchResource> {
	
    private PagedModel<EntityModel<ProductResource>> products;
    
    private Set<IFacet> facets;
    
	public SearchResource(String locale, 
						  String currency, 
						  String category,
						  String term,
						  int page,
						  int size,
						  String sortBy,
						  Set<IFacet> searchFacets,
  						  PagedResourcesAssembler<ProductResource> assembler,
  						  RepresentationModelAssemblerSupport<Product, ProductResource> prodAssembler,
  						  IProductPortService ipps) {

		
		final Set<IFacet> returnFacets = new HashSet<IFacet>();
		
    	//get the resulting pages of product
    	final Page<Product> pages = ipps.search(	locale, 
    												currency, 
    												PageRequest.of(page, size),
    												category, 
    												term, 
    												searchFacets,
    												returnFacets);
    	
    	//convert the page of products to a page of product resources
    	final Page<ProductResource> prPages = new PageImpl<ProductResource>(pages.stream()
							    											.map(p -> prodAssembler.toModel(p))
							    											.collect(Collectors.toList()),
							    											pages.getPageable(),
							    											pages.getTotalElements());
    	
    	this.products = (assembler.toModel(prPages));
    	
		this.facets = returnFacets;
    }

	public PagedModel<EntityModel<ProductResource>> getProducts() {
		return products;
	}

	public Set<IFacet> getFacets() {
		return facets;
	}
	
}
