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
import io.nzbee.domain.product.shipping.ShippingProduct;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.IBagService;
import io.nzbee.domain.brand.IBrandService;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.brand.view.IBrandViewMapper;
import io.nzbee.resources.brand.BrandResource;
import io.nzbee.resources.brand.BrandResourceAssembler;
import io.nzbee.resources.dto.BrowseProductResultDto;
import io.nzbee.resources.product.physical.full.PhysicalProductFullResource;
import io.nzbee.resources.product.physical.full.PhysicalProductFullResourceAssembler;
import io.nzbee.resources.product.physical.light.PhysicalProductLightResource;
import io.nzbee.resources.product.physical.light.PhysicalProductLightResourceAssembler;
import io.nzbee.resources.product.shipping.ShippingProductResource;
import io.nzbee.resources.product.shipping.ShippingProductResourceAssembler;
import io.nzbee.resources.product.shipping.destination.ShippingDestinationResource;
import io.nzbee.resources.product.shipping.destination.ShippingDestinationResourceAssembler;
import io.nzbee.resources.product.shipping.type.ShippingTypeResource;
import io.nzbee.resources.product.shipping.type.ShippingTypeResourceAssembler;
import io.nzbee.search.facet.IFacet;
import io.nzbee.view.ports.IPhysicalProductFullPortService;
import io.nzbee.view.ports.IPhysicalProductLightPortService;
import io.nzbee.view.ports.IShippingProductPortService;
import io.nzbee.view.product.brand.BrandView;
import io.nzbee.view.product.physical.PhysicalProductFullView;
import io.nzbee.view.product.physical.PhysicalProductLightView;
import io.nzbee.view.product.shipping.destination.ShippingDestinationView;
import io.nzbee.view.product.shipping.type.ShippingTypeView;

@RestController
@RequestMapping("/api")
public class ProductController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private IPhysicalProductFullPortService physicalProductFullPortService;

	@Autowired
	private IPhysicalProductLightPortService physicalProductLightPortService;

	@Autowired
	private IBagService bagService;

	@Autowired
	private IBrandService brandService;

	@Autowired
	private IShippingProductPortService shippingProductService; 

	@Autowired
	private IBrandViewMapper brandDTOMapper;

	@Autowired
	private ShippingDestinationResourceAssembler shippingDestinationResourceAssembler;

	@Autowired
	private ShippingTypeResourceAssembler shippingTypeResourceAssembler;

	@Autowired
	private ShippingProductResourceAssembler prodShippingResourceAssembler;
	
	@Autowired
	private PhysicalProductLightResourceAssembler prodLightResourceAssembler;

	@Autowired
	private BrandResourceAssembler brandResourceAssembler;

	@Autowired
	private PhysicalProductFullResourceAssembler prodFullResourceAssembler;

	@Autowired
	private PagedResourcesAssembler<PhysicalProductLightResource> prodPhysicalPagedAssembler;

	@GetMapping(value = "/Product/Physical/{locale}/{currency}/category/{categoryCode}")
	public ResponseEntity<BrowseProductResultDto> getPhysicalProducts(@PathVariable String locale,
			@PathVariable String currency, @PathVariable String categoryCode,
			@RequestParam(value = "page", defaultValue = "0") String page,
			@RequestParam(value = "size", defaultValue = "10") String size,
			@RequestParam(value = "sort", defaultValue = "10") String sort) {

		LOGGER.debug("Fetching products for parameters : {}, {}, {}, {}, {}", locale, currency, categoryCode, page,
				size);

		final Page<PhysicalProductLightView> sp = physicalProductLightPortService.findAll(locale, currency,
				categoryCode, new StringCollectionWrapper(new HashSet<String>()),
				new StringCollectionWrapper(new HashSet<String>()), new StringCollectionWrapper(new HashSet<String>()),
				null, page, size, sort);

		final Page<PhysicalProductLightResource> pages = sp.map(p -> prodLightResourceAssembler.toModel(p));

		return ResponseEntity.ok(new BrowseProductResultDto(prodPhysicalPagedAssembler.toModel(pages)));
	}

	@GetMapping("/Product/{locale}/{currency}/code/{code}")
	public ResponseEntity<PhysicalProductFullResource> get(@PathVariable String locale, @PathVariable String currency,
			@PathVariable String code) {
		PhysicalProductFullResource pr = prodFullResourceAssembler.toModel(
				(PhysicalProductFullView) physicalProductFullPortService.findByCode(locale, currency, code).get());
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

	@PostMapping(value = "/Product/{locale}/{currency}/category/{categoryCode}", params = { "page", "size", "sort" })
	public ResponseEntity<BrowseProductResultDto> getProducts(@PathVariable String locale,
			@PathVariable String currency, @PathVariable String categoryCode, @RequestParam("page") String page,
			@RequestParam("size") String size, @RequestParam("sort") String sort,
			@RequestBody Set<IFacet> selectedFacets) {

		LOGGER.debug("call ProductController.getProducts with parameters : {}, {}, {}, {}, {}, {}, {}", locale,
				currency, categoryCode, page, size, sort, selectedFacets.size());

		Optional<String> oMaxPrice = selectedFacets.stream().filter(p -> p.getFacetingName().equals("price"))
				.map(p -> p.getId()).findFirst();
		Double maxPrice = null;
		if (oMaxPrice.isPresent()) {
			maxPrice = new Double(oMaxPrice.get());
		}

		final Page<PhysicalProductLightView> sp = physicalProductLightPortService.findAll(locale, currency,
				categoryCode,
				new StringCollectionWrapper(selectedFacets.stream().filter(c -> c.getFacetingName().equals("category"))
						.map(c -> c.getValue()).collect(Collectors.toSet())),
				new StringCollectionWrapper(selectedFacets.stream().filter(c -> c.getFacetingName().equals("brand"))
						.map(c -> c.getValue()).collect(Collectors.toSet())),
				new StringCollectionWrapper(selectedFacets.stream().filter(c -> c.getFacetingName().equals("tag"))
						.map(c -> c.getValue()).collect(Collectors.toSet())),
				maxPrice, page, size, sort);

		final Page<PhysicalProductLightResource> pages = sp.map(p -> prodLightResourceAssembler.toModel(p));

		return ResponseEntity.ok(new BrowseProductResultDto(prodPhysicalPagedAssembler.toModel(pages)));
	}

	@GetMapping(value = "/Product/Shipping/Provider/{locale}/{currency}/Code/{providerCode}")
	public ResponseEntity<ShippingTypeResource> getShippingType(@PathVariable String locale,

			@PathVariable String currency,

			@PathVariable String providerCode) {
		LOGGER.debug("Fetching shipping destination for parameters : {}, {}, {}", locale, currency, providerCode);

		ShippingTypeResource sd = shippingTypeResourceAssembler
				.toModel(shippingProductService.findTypeByCode(locale, providerCode).get());

		return new ResponseEntity<ShippingTypeResource>(sd, HttpStatus.OK);

	}

	@GetMapping(value = "/Product/Shipping/Provider/{locale}/{currency}")
	public ResponseEntity<CollectionModel<BrandResource>> getShippingProviders(@PathVariable String locale,

			@PathVariable String currency) {

		LOGGER.debug("Fetching products for parameters : {}, {}", locale, currency);

		final List<BrandView> lb = brandService.findByAllProductType(locale, ShippingProduct.class).stream()
				.map(b -> brandDTOMapper.toView(b)).collect(Collectors.toList());

		return ResponseEntity.ok(brandResourceAssembler.toCollectionModel(lb));
	}

	@GetMapping(value = "/Product/Shipping/Destination/{locale}/{currency}")
	public ResponseEntity<CollectionModel<ShippingDestinationResource>> getShippingDestinations(
			@PathVariable String locale,

			@PathVariable String currency, Principal principal) {

		LOGGER.debug("call ProductController.getShippingDestinations : {}, {}", locale, currency);

		Bag b = bagService.findByCode(locale, currency, principal.getName());

		final List<ShippingDestinationView> sp = shippingProductService
				.findAllActiveByBagWeight(locale, b.getTotalWeight());

		return ResponseEntity.ok(shippingDestinationResourceAssembler.toCollectionModel(sp));
	}

	@GetMapping(value = "/Product/Shipping/Type/{locale}/{currency}")
	public ResponseEntity<CollectionModel<ShippingTypeResource>> getShippingTypes(@PathVariable String locale,

			@PathVariable String currency) {

		LOGGER.debug("call ProductController.getShippingTypes : {}, {}", locale, currency);

		final List<ShippingTypeView> sp = shippingProductService.findAll(locale);

		return ResponseEntity.ok(shippingTypeResourceAssembler.toCollectionModel(sp));
	}

	@GetMapping(value = "/Product/Shipping/Destination/{locale}/{currency}/Code/{destinationCode}")
	public ResponseEntity<ShippingDestinationResource> getShippingDestination(@PathVariable String locale,

			@PathVariable String currency,

			@PathVariable String destinationCode) {
		LOGGER.debug("Fetching shipping destination for parameters : {}, {}, {}", locale, currency, destinationCode);

		ShippingDestinationResource sd = shippingDestinationResourceAssembler.toModel(
				shippingProductService.findDestinationByCode(locale, destinationCode).get());

		return new ResponseEntity<ShippingDestinationResource>(sd, HttpStatus.OK);

	}

	@GetMapping(value = "/Product/Shipping/Type/{locale}/{currency}/Destination/Code/{destinationCode}")
	public ResponseEntity<CollectionModel<ShippingTypeResource>> getShippingTypesByDestination(
			@PathVariable String locale,

			@PathVariable String currency,

			@PathVariable String destinationCode, Principal principal) {

		LOGGER.debug("call ProductController.getShippingTypesByDestination : {}, {}, {}", locale, currency,
				destinationCode);

		Bag b = bagService.findByCode(locale, currency, principal.getName());

		final List<ShippingTypeView> sp = shippingProductService.findAllTypesByDestinationAndWeight(locale, destinationCode, b.getTotalWeight());

		return ResponseEntity.ok(shippingTypeResourceAssembler.toCollectionModel(sp));
	}

	
	@GetMapping("/Product/{locale}/{currency}/Destination/{code}/Type/{type}")
	public ResponseEntity<ShippingProductResource> getByDestinationAndType(@PathVariable String locale,

			@PathVariable String currency,

			@PathVariable String code,

			@PathVariable String type, Principal principal) {
		Bag b = bagService.findByCode(locale, currency, principal.getName());
 
		ShippingProductResource pr = prodShippingResourceAssembler
				.toModel(shippingProductService.findByDestinationAndTypeAndBagWeight(locale, currency, code, type, b.getTotalWeight()));

		return new ResponseEntity<>(pr, HttpStatus.OK);
	}
	
}
