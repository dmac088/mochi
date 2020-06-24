package io.nzbee.resources.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.ports.IProductPortService;
import io.nzbee.domain.product.Product;
import io.nzbee.resources.product.ProductResource;
import io.nzbee.search.dto.facet.FacetContainer;

@RestController
@RequestMapping("/api")
public class NavigationController {

	@Autowired 
	private IProductPortService navigationService;
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/Product/{locale}/{currency}/category/{category}/maxPrice/{price}/sortBy/{sortBy}",
				 params = { "page", "size" })
	public ResponseEntity<PagedModel<ProductResource>> getProducts(	
										@PathVariable String locale, 
										@PathVariable String currency, 
										@PathVariable String category,
										@PathVariable Double price, 
										@RequestParam("page") int page,
								    	@RequestParam("size") int size, 
										@PathVariable String sortBy,
										@RequestBody final FacetContainer selectedFacets,
			    						@SuppressWarnings("rawtypes") PagedResourcesAssembler assembler,
			    						RepresentationModelAssemblerSupport<Product, ProductResource> prodAssembler) {
		
		final Page<Product> pages = navigationService.findAll(	locale, 
																currency, 
																price, 
																page, 
																size, 
																category, 
																selectedFacets.getFacets(), 
																sortBy);
		
		final Page<ProductResource> prPages = new PageImpl<ProductResource>(pages.stream()
				.map(p -> prodAssembler.toModel(p))
				.collect(Collectors.toList()),
				pages.getPageable(),
				pages.getTotalElements());
	   	
		return new ResponseEntity< >(assembler.toModel(prPages), HttpStatus.OK); 
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/Product/{locale}/{currency}/category/{category}/sortBy/{sortBy}",
			 	 params = { "page", "size" })
	public ResponseEntity<PagedModel<ProductResource>> getProducts(	
										@PathVariable String 			locale, 
										@PathVariable String 			currency, 
										@PathVariable 					String 	category,
										@RequestParam("page")			int page,
								    	@RequestParam("size") 			int size, 
										@PathVariable 					String 	sortBy,
										@RequestBody final 				FacetContainer selectedFacets,
			    						@SuppressWarnings("rawtypes") 	PagedResourcesAssembler assembler) {
		
		final Page<Product> pages = navigationService.findAll(	locale, 
																currency, 
																category, 
																page, 
																size, 
																selectedFacets.getFacets(), 
																sortBy);
		
		final Page<ProductResource> prPages = new PageImpl<ProductResource>(pages.stream()
				.map(p -> new ProductResource(p))
				.collect(Collectors.toList()),
				pages.getPageable(),
				pages.getTotalElements());
		
		return new ResponseEntity< >(assembler.toModel(prPages), HttpStatus.OK); 
	}
	
}
