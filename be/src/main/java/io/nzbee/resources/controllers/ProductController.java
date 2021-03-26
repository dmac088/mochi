package io.nzbee.resources.controllers;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
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
import io.nzbee.domain.product.physical.IPhysicalProductService;
import io.nzbee.domain.product.physical.PhysicalProduct;
import io.nzbee.domain.product.shipping.IShippingProductService;
import io.nzbee.domain.product.shipping.ShippingProduct;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.IBagService;
import io.nzbee.domain.brand.IBrandService;
import io.nzbee.entity.product.shipping.destination.IShippingDestinationService;
import io.nzbee.entity.product.shipping.type.IShippingTypeService;
import io.nzbee.resources.brand.BrandResource;
import io.nzbee.resources.brand.BrandResourceAssembler;
import io.nzbee.resources.dto.BrowseProductResultDto;
import io.nzbee.resources.dto.BrowseShippingProductResultDto;
import io.nzbee.resources.product.ProductResource;
import io.nzbee.resources.product.ProductResourceAssembler;
import io.nzbee.resources.product.physical.PhysicalProductResource;
import io.nzbee.resources.product.physical.PhysicalProductResourceAssembler;
import io.nzbee.resources.product.shipping.ShippingProductResource;
import io.nzbee.resources.product.shipping.ShippingProductResourceAssembler;
import io.nzbee.resources.product.shipping.destination.ShippingDestinationResource;
import io.nzbee.resources.product.shipping.destination.ShippingDestinationResourceAssembler;
import io.nzbee.resources.product.shipping.type.ShippingTypeResource;
import io.nzbee.resources.product.shipping.type.ShippingTypeResourceAssembler;
import io.nzbee.search.facet.IFacet;
import io.nzbee.view.product.IProductDTOMapper;
import io.nzbee.view.product.ProductDTO;
import io.nzbee.view.product.brand.BrandDTO;
import io.nzbee.view.product.brand.IBrandDTOMapper;
import io.nzbee.view.product.physical.IPhysicalProductDTOMapper;
import io.nzbee.view.product.shipping.IShippingProductDTOMapper;
import io.nzbee.view.product.shipping.ShippingProductDTO;
import io.nzbee.view.product.shipping.destination.IShippingDestinationDTOMapper;
import io.nzbee.view.product.shipping.destination.ShippingDestinationDTO;
import io.nzbee.view.product.shipping.type.IShippingTypeDTOMapper;
import io.nzbee.view.product.shipping.type.ShippingTypeDTO;

@RestController
@RequestMapping("/api")
public class ProductController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private IBagService bagService;
	
    @Autowired
    private IProductService productService;
    
    @Autowired
    private IPhysicalProductService physicalProductService;
    
    @Autowired
    private IShippingProductService shippingProductService;
    
    @Autowired
    private IShippingDestinationService shippingDestinationService;
    
    @Autowired
    private IShippingTypeService shippingTypeService;
    
    @Autowired
    private IBrandService brandService;
    
    @Autowired
    private IBrandDTOMapper brandDTOMapper;
    
    @Autowired
    private IProductDTOMapper productDTOMapper;
    
    @Autowired
    private IPhysicalProductDTOMapper physicalProductDTOMapper;
    
    @Autowired
    private IShippingProductDTOMapper shippingProductDTOMapper;
    
    @Autowired
    private IShippingProductDTOMapper shippingDTOMapper;
    
    @Autowired
    private IShippingDestinationDTOMapper shippingDestinationDTOMapper;
    
    @Autowired
    private IShippingTypeDTOMapper shippingTypeDTOMapper;
    
    @Autowired
    private ShippingDestinationResourceAssembler shippingDestinationResourceAssembler;
    
    @Autowired
    private ShippingTypeResourceAssembler shippingTypeResourceAssembler;
    
    @Autowired
    private ShippingProductResourceAssembler shippingProductResourceAssembler;
    
    @Autowired
    private ProductResourceAssembler prodResourceAssembler;
    
    @Autowired
    private BrandResourceAssembler brandResourceAssembler;
    
    @Autowired
    private PhysicalProductResourceAssembler prodFullResourceAssembler;
    
    @Autowired
    private ShippingProductResourceAssembler prodShippingResourceAssembler;
    
    @Autowired
    private PagedResourcesAssembler<ProductResource> prodPagedAssembler;
    
    @Autowired
    private PagedResourcesAssembler<ShippingProductResource> shippingProductPagedAssembler;
  
	@GetMapping(value = "/Product/{locale}/{currency}/category/{categoryCode}")
    public ResponseEntity<BrowseProductResultDto> getProducts(	@PathVariable String locale, 
														@PathVariable String currency, 
														@PathVariable String categoryCode,
														@RequestParam(value = "page", defaultValue = "0") String page,
														@RequestParam(value = "size", defaultValue = "10") String size,
														@RequestParam(value = "sort", defaultValue = "10") String sort) {
    	
    	LOGGER.debug("Fetching products for parameters : {}, {}, {}, {}, {}", locale, currency, categoryCode, page, size);
    	
    	final Page<ProductDTO> sp = productService.findAll(	locale, 
															currency, 
															categoryCode, 
															new HashSet<String>(), 
															new HashSet<String>(),
															new HashSet<String>(),
															null,
															page, 
															size, 
															sort).map(d -> productDTOMapper.toDto(d));
    	
    	final Page<ProductResource> pages = sp.map(p -> prodResourceAssembler.toModel(p));
    			
    	return ResponseEntity.ok(new BrowseProductResultDto(prodPagedAssembler.toModel(pages)));
    }
    
	@GetMapping(value = "/Product/Physical/{locale}/{currency}/category/{categoryCode}")
    public ResponseEntity<BrowseProductResultDto> getPhysicalProducts(	@PathVariable String locale, 
														@PathVariable String currency, 
														@PathVariable String categoryCode,
														@RequestParam(value = "page", defaultValue = "0") String page,
														@RequestParam(value = "size", defaultValue = "10") String size,
														@RequestParam(value = "sort", defaultValue = "10") String sort) {
    	
    	LOGGER.debug("Fetching products for parameters : {}, {}, {}, {}, {}", locale, currency, categoryCode, page, size);
    	
    	final Page<ProductDTO> sp = physicalProductService.findAll(	
    														locale, 
															currency, 
															categoryCode, 
															new HashSet<String>(), 
															new HashSet<String>(),
															new HashSet<String>(),
															null,
															page, 
															size, 
															sort).map(d -> productDTOMapper.toDto(d));
    	
    	final Page<ProductResource> pages = sp.map(p -> prodResourceAssembler.toModel(p));
    			
    	return ResponseEntity.ok(new BrowseProductResultDto(prodPagedAssembler.toModel(pages)));
    }
	
	
	@GetMapping(value = "/Product/Shipping/{locale}/{currency}/category/{categoryCode}")
    public ResponseEntity<BrowseShippingProductResultDto> getShippingProducts(	@PathVariable String locale, 
																@PathVariable String currency, 
																@PathVariable String categoryCode,
																@RequestParam(value = "page", defaultValue = "0") String page,
																@RequestParam(value = "size", defaultValue = "10") String size,
																@RequestParam(value = "sort", defaultValue = "10") String sort) {
    	
    	LOGGER.debug("Fetching products for parameters : {}, {}, {}, {}, {}", locale, currency, categoryCode, page, size);
    	
    	final Page<ShippingProductDTO> sp = shippingProductService.findAll(	locale, 
															currency, 
															categoryCode, 
															new HashSet<String>(), 
															new HashSet<String>(),
															new HashSet<String>(),
															null,
															page, 
															size, 
															sort).map(d -> shippingDTOMapper.toDto(d));
    	
    	final Page<ShippingProductResource> pages = sp.map(p -> shippingProductResourceAssembler.toModel(p));
    			
    	return ResponseEntity.ok(new BrowseShippingProductResultDto(shippingProductPagedAssembler.toModel(pages)));
    }
	
	@GetMapping(value = "/Product/Shipping/Destination/{locale}/{currency}")
    public ResponseEntity<CollectionModel<ShippingDestinationResource>> getShippingDestinations(	@PathVariable String locale, 
																							   		@PathVariable String currency,
																							   		Principal principal) {
    	
    	LOGGER.debug("call ProductController.getShippingDestinations : {}, {}", locale, currency);
    	
    	Bag b = bagService.findByCode(	locale, 
										currency, 
										principal.getName());
    	
    	final List<ShippingDestinationDTO> sp = shippingDestinationService.findAllActiveByBagWeight(locale, b.getTotalWeight()) 
															.stream().map(d -> shippingDestinationDTOMapper.toDto(d))
															.collect(Collectors.toList());
    	
    	
    	return ResponseEntity.ok(shippingDestinationResourceAssembler.toCollectionModel(sp));
    }
	
	@GetMapping(value = "/Product/Shipping/Destination/{locale}/{currency}/Code/{destinationCode}")
	public ResponseEntity<ShippingDestinationResource> getShippingDestination(@PathVariable String locale, 
																			  @PathVariable String currency,
																			  @PathVariable String destinationCode) {
		LOGGER.debug("Fetching shipping destination for parameters : {}, {}, {}", locale, currency, destinationCode);
		
		ShippingDestinationResource sd = shippingDestinationResourceAssembler.toModel(
				shippingDestinationDTOMapper.toDto(shippingDestinationService.findByCode(locale, destinationCode).get()));
		
		return new ResponseEntity<ShippingDestinationResource>(sd, HttpStatus.OK);
		
	}
	
	
	@GetMapping(value = "/Product/Shipping/Type/{locale}/{currency}")
    public ResponseEntity<CollectionModel<ShippingTypeResource>> getShippingTypes(	@PathVariable String locale, 
																					@PathVariable String currency) {
    	
    	LOGGER.debug("call ProductController.getShippingTypes : {}, {}", locale, currency);
    	
    	final List<ShippingTypeDTO> sp = shippingTypeService.findAll(locale) 
															.stream().map(d -> shippingTypeDTOMapper.toDto(d))
															.collect(Collectors.toList());
    	
    	
    	return ResponseEntity.ok(shippingTypeResourceAssembler.toCollectionModel(sp));
    }
	
	@GetMapping(value = "/Product/Shipping/Type/{locale}/{currency}/Destination/Code/{destinationCode}")
    public ResponseEntity<CollectionModel<ShippingTypeResource>> getShippingTypesByDestination(	@PathVariable String locale, 
																								@PathVariable String currency,
																								@PathVariable String destinationCode) {
    	
    	LOGGER.debug("call ProductController.getShippingTypesByDestination : {}, {}, {}", locale, currency, destinationCode);
    	
    	final List<ShippingTypeDTO> sp = shippingTypeService.findAll(locale, destinationCode) 
															.stream().map(d -> shippingTypeDTOMapper.toDto(d))
															.collect(Collectors.toList());
    	
    	
    	return ResponseEntity.ok(shippingTypeResourceAssembler.toCollectionModel(sp));
    }
	
	@GetMapping(value = "/Product/Shipping/Provider/{locale}/{currency}/Code/{providerCode}")
	public ResponseEntity<ShippingTypeResource> getShippingType(	  	  @PathVariable String locale, 
																		  @PathVariable String currency,
																		  @PathVariable String providerCode) {
		LOGGER.debug("Fetching shipping destination for parameters : {}, {}, {}", locale, currency, providerCode);
		
		ShippingTypeResource sd = shippingTypeResourceAssembler.toModel(
				shippingTypeDTOMapper.toDto(shippingTypeService.findByCode(locale, providerCode).get()));
		
		return new ResponseEntity<ShippingTypeResource>(sd, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/Product/Shipping/Provider/{locale}/{currency}")
    public ResponseEntity<CollectionModel<BrandResource>> getShippingProviders(@PathVariable String locale, 
																			   @PathVariable String currency) {
    	
    	LOGGER.debug("Fetching products for parameters : {}, {}", locale, currency);
    	
    	final List<BrandDTO> lb = brandService.findByAllProductType(locale, ShippingProduct.class)
    							  .stream()
    							  .map(b -> brandDTOMapper.toDto(b))
    							  .collect(Collectors.toList());
    	
    	return ResponseEntity.ok(brandResourceAssembler.toCollectionModel(lb));
    }
	
    
    @GetMapping("/Product/{locale}/{currency}/code/{code}")
    public ResponseEntity<PhysicalProductResource> get(	@PathVariable String locale, 
    											@PathVariable String currency, 
    											@PathVariable String code) {
    	PhysicalProductResource pr = prodFullResourceAssembler.toModel(physicalProductDTOMapper.toDto(productService.findByCode(locale, currency, code)));
    	return new ResponseEntity< >(pr, HttpStatus.OK);
    }
    
    @GetMapping("/Product/{locale}/{currency}/Destination/{code}/Type/{type}")
    public ResponseEntity<ShippingProductResource> getByDestinationAndType(				@PathVariable String locale, 
										    											@PathVariable String currency, 
										    											@PathVariable String code,
										    											@PathVariable String type,
										    											Principal principal) {
    	Bag b = bagService.findByCode(	locale, 
				currency, 
				principal.getName());
    	
    	ShippingProductResource pr = prodShippingResourceAssembler.toModel(shippingProductDTOMapper.toDto(
    							shippingProductService.findByDestinationAndTypeAndWeight(locale, currency, code, type, b.getTotalWeight())));
    	
    	return new ResponseEntity< >(pr, HttpStatus.OK);
    }
    
    @PostMapping("/Product/{locale}/{currency}")
    public ResponseEntity<CollectionModel<ProductResource>> getProducts(	@PathVariable String locale, 
    																		@PathVariable String currency, 
    																		@RequestBody final Set<String> productCodes) {
    	
    	LOGGER.debug("Fetching product for parameters : {}, {}, {}}", locale, currency, productCodes);
    	
    	final Set<ProductDTO> collection = productService.findAll(locale, currency, productCodes)
    													 .stream()
    													 .map(p -> productDTOMapper.toDto(p))
    													 .collect(Collectors.toSet());
    	
        return ResponseEntity.ok(prodResourceAssembler.toCollectionModel(collection));
    }
    
  
	@PostMapping(value = "/Product/{locale}/{currency}/category/{categoryCode}",
			 	 params = { "page", "size", "sort" })
	public ResponseEntity<BrowseProductResultDto> getProducts(	
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

		final Page<PhysicalProduct> sp = physicalProductService.findAll(locale, 
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
		
		final Page<ProductResource> pages = sp.map(p -> prodResourceAssembler.toModel(productDTOMapper.toDto(p)));
		
		return ResponseEntity.ok(new BrowseProductResultDto(prodPagedAssembler.toModel(pages)));
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
