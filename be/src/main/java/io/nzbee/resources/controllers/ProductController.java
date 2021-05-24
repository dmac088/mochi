package io.nzbee.resources.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
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
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.IBagService;
import io.nzbee.resources.brand.BrandViewModel;
import io.nzbee.resources.brand.BrandViewModelAssembler;
import io.nzbee.resources.dto.BrowseProductResultDto;
import io.nzbee.resources.product.physical.full.PhysicalProductFullResource;
import io.nzbee.resources.product.physical.full.PhysicalProductFullResourceAssembler;
import io.nzbee.resources.product.physical.light.PhysicalProductLightModel;
import io.nzbee.resources.product.physical.light.PhysicalProductLightModelAssembler;
import io.nzbee.resources.product.shipping.ShippingProductResource;
import io.nzbee.resources.product.shipping.ShippingProductResourceAssembler;
import io.nzbee.resources.product.shipping.destination.ShippingDestinationResource;
import io.nzbee.resources.product.shipping.destination.ShippingDestinationResourceAssembler;
import io.nzbee.resources.product.shipping.type.ShippingTypeResource;
import io.nzbee.resources.product.shipping.type.ShippingTypeResourceAssembler;
import io.nzbee.search.facet.IFacet;
import io.nzbee.view.ports.IShippingProductPortService;
import io.nzbee.view.product.brand.BrandView;
import io.nzbee.view.product.brand.IBrandViewService;
import io.nzbee.view.product.physical.full.IPhysicalProductFullService;
import io.nzbee.view.product.physical.full.PhysicalProductFullView;
import io.nzbee.view.product.physical.light.IPhysicalProductLightService;
import io.nzbee.view.product.physical.light.PhysicalProductLightView;
import io.nzbee.view.product.shipping.destination.ShippingDestinationView;
import io.nzbee.view.product.shipping.type.ShippingTypeView;

@RestController
@RequestMapping("/api")
public class ProductController {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IPhysicalProductFullService physicalProductFullService;

	@Autowired
	private IPhysicalProductLightService physicalProductLightService;

	@Autowired
	private IBagService bagService;
	
	@Autowired
	private IBrandViewService brandService;

	@Autowired
	private IShippingProductPortService shippingProductService; 

	@Autowired
	private ShippingDestinationResourceAssembler shippingDestinationResourceAssembler;

	@Autowired
	private ShippingTypeResourceAssembler shippingTypeResourceAssembler;

	@Autowired
	private ShippingProductResourceAssembler prodShippingResourceAssembler;
	
	@Autowired
	private PhysicalProductLightModelAssembler prodLightResourceAssembler;

	@Autowired
	private BrandViewModelAssembler brandResourceAssembler;

	@Autowired
	private PhysicalProductFullResourceAssembler prodFullResourceAssembler;

	@Autowired
	private PagedResourcesAssembler<PhysicalProductLightModel> prodPhysicalPagedAssembler;

	@GetMapping("/Product/{locale}/{currency}/Code/{code}")
	public ResponseEntity<PhysicalProductFullResource> get(@PathVariable String locale, @PathVariable String currency,
			@PathVariable String code) {
		PhysicalProductFullResource pr = prodFullResourceAssembler.toModel(
				(PhysicalProductFullView) physicalProductFullService.findByCode(locale, currency, code));
		return new ResponseEntity<>(pr, HttpStatus.OK);
	}

	@GetMapping(value = "/Product/Image/{imageFileName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody ResponseEntity<byte[]> getImageWithMediaType(@PathVariable String imageFileName) {
		LOGGER.debug("call ProductController.getImageWithMediaType with parameter {}", imageFileName);
		InputStream in = getClass().getResourceAsStream("/public/images/" + imageFileName);
		try {
			if (!(in == null)) {
				return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), HttpStatus.OK);
			}
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/Product/{locale}/{currency}/Category/Code/{code}", params = { "page", "size", "sort" })
	public ResponseEntity<BrowseProductResultDto> getProducts(	@PathVariable String locale,
																@PathVariable String currency, 
																@PathVariable String code, 
																@RequestParam("page") String page,
																@RequestParam("size") String size, 
																@RequestParam("sort") String sort,
																@RequestBody Set<IFacet> selectedFacets) {

		LOGGER.debug("call ProductController.getProducts with parameters : {}, {}, {}, {}, {}, {}, {}", locale, currency, code, page, size, sort, selectedFacets.size());

		Optional<String> oMaxPrice = selectedFacets.stream().filter(p -> p.getFacetingName().equals("price"))
				.map(p -> p.getId()).findFirst();
		Double maxPrice = null;
		if (oMaxPrice.isPresent()) {
			maxPrice = Double.valueOf(oMaxPrice.get());
		}

		Page<PhysicalProductLightView> sp = physicalProductLightService.findAll(locale, currency,
				code,
				selectedFacets.stream().filter(c -> c.getFacetingName().equals("category"))
						.map(c -> c.getValue()).collect(Collectors.toSet()),
				selectedFacets.stream().filter(c -> c.getFacetingName().equals("brand"))
						.map(c -> c.getValue()).collect(Collectors.toSet()),
				selectedFacets.stream().filter(c -> c.getFacetingName().equals("tag"))
						.map(c -> c.getValue()).collect(Collectors.toSet()),
				maxPrice, page, size, sort);

		Page<PhysicalProductLightModel> pages = sp.map(p -> prodLightResourceAssembler.toModel(p));

		return ResponseEntity.ok(new BrowseProductResultDto(prodPhysicalPagedAssembler.toModel(pages)));
	}

	@GetMapping(value = "/Product/Shipping/Provider/{locale}/{currency}/Code/{code}")
	public ResponseEntity<ShippingTypeResource> getShippingType(@PathVariable String locale,
																@PathVariable String currency,
																@PathVariable String code) {
		LOGGER.debug("Fetching shipping destination for parameters : {}, {}, {}", locale, currency, code);

		ShippingTypeResource sd = shippingTypeResourceAssembler
				.toModel(shippingProductService.findTypeByCode(locale, code).get());

		return new ResponseEntity<ShippingTypeResource>(sd, HttpStatus.OK);

	}

	@GetMapping(value = "/Product/Shipping/Provider/{locale}/{currency}")
	public ResponseEntity<CollectionModel<BrandViewModel>> getShippingProviders(@PathVariable String locale,

			@PathVariable String currency) {

		LOGGER.debug("Fetching products for parameters : {}, {}", locale, currency);

		List<BrandView> lb = brandService.findByAllShippingProviders(locale);

		return ResponseEntity.ok(brandResourceAssembler.toCollectionModel(lb));
	}

	@GetMapping(value = "/Product/Shipping/Destination/{locale}/{currency}")
	public ResponseEntity<CollectionModel<ShippingDestinationResource>> getShippingDestinations(@PathVariable String locale,
																								@PathVariable String currency, Principal principal) {

		LOGGER.debug("call ProductController.getShippingDestinations : {}, {}", locale, currency);

		Bag b = bagService.findByCode(locale, currency, principal.getName());

		List<ShippingDestinationView> sp = shippingProductService
				.findAllActiveByBagWeight(locale, b.getTotalWeight());

		return ResponseEntity.ok(shippingDestinationResourceAssembler.toCollectionModel(sp));
	}

	@GetMapping(value = "/Product/Shipping/Destination/{locale}/{currency}/Code/{code}")
	public ResponseEntity<ShippingDestinationResource> getShippingDestination(	@PathVariable String locale,
																				@PathVariable String currency,
																				@PathVariable String code) {
		LOGGER.debug("Fetching shipping destination for parameters : {}, {}, {}", locale, currency, code);

		ShippingDestinationResource sd = shippingDestinationResourceAssembler.toModel(
				shippingProductService.findDestinationByCode(locale, code).get());

		return new ResponseEntity<ShippingDestinationResource>(sd, HttpStatus.OK);

	}

	@GetMapping(value = "/Product/Shipping/Type/{locale}/{currency}/Destination/Code/{code}")
	public ResponseEntity<CollectionModel<ShippingTypeResource>> getShippingTypesByDestination(	@PathVariable String locale,
																								@PathVariable String currency,
																								@PathVariable String code, 
																								Principal principal) {

		LOGGER.debug("call ProductController.getShippingTypesByDestination : {}, {}, {}", locale, currency, code);

		Bag b = bagService.findByCode(locale, currency, principal.getName());

		List<ShippingTypeView> sp = shippingProductService.findAllTypesByDestinationAndWeight(locale, code, b.getTotalWeight());

		return ResponseEntity.ok(shippingTypeResourceAssembler.toCollectionModel(sp));
	}

	
	@GetMapping("/Product/{locale}/{currency}/Destination/{code}/Type/{type}")
	public ResponseEntity<ShippingProductResource> getByDestinationAndType(	@PathVariable String locale,
																			@PathVariable String currency,
																			@PathVariable String code,
																			@PathVariable String type, 
																			Principal principal) {
		Bag b = bagService.findByCode(locale, currency, principal.getName());
 
		ShippingProductResource pr = prodShippingResourceAssembler
				.toModel(shippingProductService.findByDestinationAndTypeAndBagWeight(locale, currency, code, type, b.getTotalWeight()));

		return new ResponseEntity<>(pr, HttpStatus.OK);
	}
	
}
