package io.nzbee.resources.controllers;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.BagItem;
import io.nzbee.domain.bag.IBagItemService;
import io.nzbee.domain.bag.IBagService;
import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.product.Product;
import io.nzbee.resources.bag.BagResource;
import io.nzbee.resources.bag.BagResourceAssembler;
import io.nzbee.resources.bag.item.BagItemResource;
import io.nzbee.resources.bag.item.BagItemResourceAssembler;
import io.nzbee.view.bag.IBagDTOMapper;
import io.nzbee.view.bag.item.BagItemDTOIn;
import io.nzbee.view.bag.item.IBagItemDTOMapper;


@RestController
@RequestMapping("/api")
public class BagController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private IBagService bagService;
    
    @Autowired
    private IBagItemService bagItemService;
    
    @Autowired
	private IProductService productService;
    
    @Autowired
    private IBagDTOMapper bagDTOMapper;
    
    @Autowired
    private IBagItemDTOMapper bagItemDTOMapper;
    
    @Autowired
    private BagResourceAssembler bagResourceAssembler; 
    
    @Autowired
    private BagItemResourceAssembler bagItemResourceAssembler; 
      

    public BagController() {
        super();
    }

    @GetMapping("/Bag/{locale}/{currency}")
	public ResponseEntity<BagResource> getCustomerBag(	@PathVariable String locale, 
														@PathVariable String currency, 
														Principal principal) {
    	LOGGER.debug("call BagController.getCustomerBag");
    	return ResponseEntity.ok(bagResourceAssembler.toModel(bagDTOMapper.toDto(bagService.findByCode(locale,
																	    								 currency,
																	    								 principal.getName()))));
	}
    
    @PostMapping("/Bag/{locale}/{currency}/Coupon/Code/{coupon}")
    public ResponseEntity<BagResource> addCouponToBag( 	@PathVariable String locale, 
														@PathVariable String currency, 
														@PathVariable String coupon, 
														Principal principal) {
	LOGGER.debug("call BagController.addCouponToBag");
	
	Bag b = bagService.findByCode(	locale,
									currency,
									principal.getName());
	
	b.setCouponCode(coupon);
	
	bagService.checkAllBagRules(b);
	
	bagService.save(b);
	
	return ResponseEntity.ok(bagResourceAssembler.toModel(bagDTOMapper.toDto(b)));
	}
    
    @GetMapping("/Bag/{locale}/{currency}/Items")
	public ResponseEntity<CollectionModel<BagItemResource>> getBagContents(@PathVariable String locale, 
													  					   @PathVariable String currency, 
													  					   Principal principal) {
    	LOGGER.debug("call BagController.getBagContents");
    	Set<BagItem> sbi =  bagService.findByCode(locale,
												   currency,
												   principal.getName()).getBagItems();
    	
    	return ResponseEntity.ok(bagItemResourceAssembler.toCollectionModel(sbi.stream()
    													 					   .map(bi -> bagItemDTOMapper.toDto(bi))
    													 					   .collect(Collectors.toSet())));
    	
	}
    
    @PostMapping("/Bag/{locale}/{currency}/Items/Add")
	public ResponseEntity<BagResource>  addItemToBag(	@PathVariable String locale, 
														@PathVariable String currency,
														@RequestBody BagItemDTOIn dto, 
														Principal principal) {
    	
    	LOGGER.debug("call BagController.addItemToBag with parameters {}, {}, {}, {}", locale, currency, dto.getItemUPC(), dto.getItemQty());
    	
    	//here we get the bag and bagItems but the products are null
    	Bag b = bagService.findByCode(	locale, 
    									currency, 
    									principal.getName());
    	
    	Product p = productService.findByCode(	locale, 
												currency, 
												dto.getItemUPC());
    	
    	boolean exists = b.bagItemExists(dto.getItemUPC());
    	
    	//find the existing bagitem first
    	BagItem bagItem = exists ? b.getBagItem(dto.getItemUPC()) : new BagItem(b, p, 0);
    		
    	bagItem.addToQuantity(dto.getItemQty());      					  
    	
    	//Run through the business rules
	    	//Checks out of stock
	    	//Checks bag and bagItem limits
	    	//Checks promotion eligibility, and applies discount 
    	bagItemService.checkAllBagItemRules(bagItem);
		
    	if(bagItem.isErrors()) {
    		return ResponseEntity.ok(bagResourceAssembler.toModel(bagDTOMapper.toDto(b)));
    	}
    	
    	if(!exists) {
    		b.addItem(bagItem);
    	}
    	
    	bagService.save(b);
    	
    	return ResponseEntity.ok(bagResourceAssembler.toModel(bagDTOMapper.toDto(b)));
	}
    
    @GetMapping("/Bag/{locale}/{currency}/Items/Remove/{itemCode}")
	public ResponseEntity<Void> removeItemFromBag(	@PathVariable String locale, 
													@PathVariable String currency,
													@PathVariable String itemCode, 
													Principal principal) {
    	
    	LOGGER.debug("call BagController.removeItemFromBag for product code {} ", itemCode);
    	//here we get the bag and bagItems but the products are null
    	Bag b = bagService.findByCode(	locale, 
    									currency, 
    									principal.getName());
    	
    	Optional<BagItem> obi = b.getBagItems().stream().filter(bi -> bi.getProduct().getProductUPC().equals(itemCode)).findAny();
    	
    	if(obi.isPresent()) {
    		bagItemService.delete(obi.get());	
    	}
		return null;
	}
 
    
}
