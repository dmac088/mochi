package io.nzbee.resources.controllers;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.product.Product;
import io.nzbee.dto.product.physical.IPhysicalProductDTOFullMapper;
import io.nzbee.dto.product.physical.IPhysicalProductDTOLightMapper;
import io.nzbee.dto.product.physical.PhysicalProductDTOLight;
import io.nzbee.resources.dto.BrowseResultDto;
import io.nzbee.resources.product.ProductFullResource;
import io.nzbee.resources.product.ProductFullResourceAssembler;
import io.nzbee.resources.product.ProductLightResource;
import io.nzbee.resources.product.ProductLightResourceAssembler;
import io.nzbee.search.facet.IFacet;

@RestController
@RequestMapping("/api")
public class ProductController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private IProductService productService;
    
    @Autowired
    private IPhysicalProductDTOFullMapper productDTOFullMapper;
    
    @Autowired
    private IPhysicalProductDTOLightMapper productDTOLightMapper;
    
    @Autowired
    private ProductLightResourceAssembler prodLightResourceAssembler;
    
    @Autowired
    private ProductFullResourceAssembler prodFullResourceAssembler;
    
    @Autowired
    private PagedResourcesAssembler<ProductLightResource> prodPagedAssembler;
    
    
	@GetMapping(value = "/Product/{locale}/{currency}/category/{categoryCode}")
    public ResponseEntity<BrowseResultDto> getProducts(	@PathVariable String locale, 
														@PathVariable String currency, 
														@PathVariable String categoryCode,
														@RequestParam(value = "page", defaultValue = "0") String page,
														@RequestParam(value = "size", defaultValue = "10") String size,
														@RequestParam(value = "sort", defaultValue = "10") String sort) {
    	
    	LOGGER.debug("Fetching products for parameters : {}, {}, {}, {}, {}", locale, currency, categoryCode, page, size);
    	
    	final Page<PhysicalProductDTOLight> sp = productService.findAll(	locale, 
															currency, 
															categoryCode, 
															new HashSet<String>(), 
															new HashSet<String>(),
															new HashSet<String>(),
															null,
															page, 
															size, 
															sort).map(d -> productDTOLightMapper.doToDto(d));
    	
    	final Page<ProductLightResource> pages = sp.map(p -> prodLightResourceAssembler.toModel(p));
    			
    	return ResponseEntity.ok(new BrowseResultDto(prodPagedAssembler.toModel(pages)));
    }
    
	@GetMapping(value = "/Product/{locale}/{currency}/brand/code/{brandCode}", 
    			params = { "page", "size" })
    public ResponseEntity<PagedModel<ProductLightResource>> getProductsByBrand(	@PathVariable String locale, 
															    	   		@PathVariable String currency, 
															    	   		@PathVariable String brandCode,
															    	   		@RequestParam("page") int page,
															    	   		@RequestParam("size") int size) {
    	return null;
    }
    
    @GetMapping("/Product/{locale}/{currency}/code/{code}")
    public ResponseEntity<ProductFullResource> get(	@PathVariable String locale, 
    											@PathVariable String currency, 
    											@PathVariable String code) {
    	ProductFullResource pr = prodFullResourceAssembler.toModel(productDTOFullMapper.doToDto(productService.findByCode(locale, currency, code)));
    	return new ResponseEntity< >(pr, HttpStatus.OK);
    }
    
    @PostMapping("/Product/{locale}/{currency}")
    public ResponseEntity<CollectionModel<ProductLightResource>> getProducts(	@PathVariable String locale, 
    																		@PathVariable String currency, 
    																		@RequestBody final Set<String> productCodes) {
    	
    	LOGGER.debug("Fetching product for parameters : {}, {}, {}}", locale, currency, productCodes);
    	
    	final Set<PhysicalProductDTOLight> collection = productService.findAll(locale, currency, productCodes)
    													 .stream()
    													 .map(p -> productDTOLightMapper.doToDto(p))
    													 .collect(Collectors.toSet());
    	
        return ResponseEntity.ok(prodLightResourceAssembler.toCollectionModel(collection));
    }
    
  
	@PostMapping(value = "/Product/{locale}/{currency}/category/{categoryCode}",
			 	 params = { "page", "size", "sort" })
	public ResponseEntity<BrowseResultDto> getProducts(	
										@PathVariable String 			locale, 
										@PathVariable String 			currency, 
										@PathVariable 					String 	categoryCode,
										@RequestParam("page")			String page,
								    	@RequestParam("size") 			String size, 
								    	@RequestParam("sort") 			String sort,
										@RequestBody  					Set<IFacet> selectedFacets) {
		
		
		LOGGER.debug("call ProductController.getProducts with parameters : {}, {}, {}, {}, {}, {}, {}", locale, currency, categoryCode, page, size, sort, selectedFacets.size());
		
		Optional<String> oMaxPrice = selectedFacets.stream().filter(p -> p.getFacetingName().equals("price")).map(p -> p.getId()).findFirst();
    	Double maxPrice = null;
    	if(oMaxPrice.isPresent()) {
    		maxPrice = new Double(oMaxPrice.get());
    	}

		final Page<Product> sp = productService.findAll(locale, 
													currency, 
													categoryCode, 
													selectedFacets.stream().filter(c -> c.getFacetingName().equals("category")).map(c -> c.getValue())
													.collect(Collectors.toSet()), 
													selectedFacets.stream().filter(c -> c.getFacetingName().equals("brand")).map(c -> c.getValue())
													.collect(Collectors.toSet()), 
													selectedFacets.stream().filter(c -> c.getFacetingName().equals("tag")).map(c -> c.getValue())
													.collect(Collectors.toSet()), 
													maxPrice,
													page, 
													size, 
													sort
										  		  );	
		
		final Page<ProductLightResource> pages = sp.map(p -> prodLightResourceAssembler.toModel(productDTOLightMapper.doToDto(p)));
		
		return ResponseEntity.ok(new BrowseResultDto(prodPagedAssembler.toModel(pages)));
	}
	
	@GetMapping(
			  value = "/Product/Image/{imageFileName}",
			  produces = MediaType.IMAGE_JPEG_VALUE
			)
	public @ResponseBody ResponseEntity<byte[]> getImageWithMediaType(@PathVariable String imageFileName) {
		LOGGER.debug("call ProductController.getImageWithMediaType with parameter {}", imageFileName);
		InputStream in = getClass().getResourceAsStream("/public/images/" + imageFileName);
		try {
			if(!(in == null)) {
					return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), HttpStatus.OK);
			} 
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
	}
}
