package io.nzbee.resources.search;

import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceSupport;
import io.nzbee.domain.product.Product;
import io.nzbee.dto.facet.IFacet;
import io.nzbee.resources.product.ProductResource;
import io.nzbee.search.ISearchService;
import lombok.Getter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedResourcesAssembler;

@Getter
public class SearchResource extends ResourceSupport {
	
    private PagedResources<Resource<ProductResource>> products;
    
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
  						  ResourceAssembler<Product, ProductResource> prodAssembler,
  						  ISearchService iss) {

		
		final Set<IFacet> returnFacets = new HashSet<IFacet>();
		
    	//get the resulting pages of product
    	final Page<Product> pages = iss.findAll(	locale, 
    												currency, 
    												category, 
    												term, 
    												page,
    												size, 
    												sortBy, 
    												searchFacets,
    												returnFacets);
    	
    	//convert the page of products to a page of product resources
    	final Page<ProductResource> prPages = new PageImpl<ProductResource>(pages.stream()
							    											.map(p -> prodAssembler.toResource(p))
							    											.collect(Collectors.toList()),
							    											pages.getPageable(),
							    											pages.getTotalElements());
    	
    	this.products = (assembler.toResource(prPages));
    	
		this.facets = returnFacets;
    }


	


	public PagedResources<Resource<ProductResource>> getProducts() {
		return products;
	}

	public Set<IFacet> getFacets() {
		return facets;
	}
	
}
