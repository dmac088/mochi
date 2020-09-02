package io.nzbee.resources.controllers;

import java.security.Principal;
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
import io.nzbee.domain.bag.IBagService;
import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.product.Product;
import io.nzbee.dto.bag.IBagDTOMapper;
import io.nzbee.dto.bag.item.BagItemDTOIn;
import io.nzbee.dto.bag.item.IBagItemDTOMapper;
import io.nzbee.resources.bag.BagResource;
import io.nzbee.resources.bag.BagResourceAssembler;
import io.nzbee.resources.bag.item.BagItemResource;
import io.nzbee.resources.bag.item.BagItemResourceAssembler;


@RestController
@RequestMapping("/api")
public class BagController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private IBagService bagService;
    
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
    	return ResponseEntity.ok(bagResourceAssembler.toModel(bagDTOMapper.doToDto(bagService.findByCode(locale,
																	    								 currency,
																	    								 principal.getName()))));
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
    													 					   .map(bi -> bagItemDTOMapper.doToDto(bi)).collect(Collectors.toSet())));
    	
	}
    
    @PostMapping("/Bag/{locale}/{currency}/Items/Add")
	public ResponseEntity<BagResource>  addItemToBag(	@PathVariable String locale, 
														@PathVariable String currency,
														@RequestBody BagItemDTOIn dto, 
														Principal principal) {
    	
    	LOGGER.debug("call BagController.addItemToBag");
    	//here we get the bag and bagItems but the products are null
    	Bag b = bagService.findByCode(	locale, 
    									currency, 
    									principal.getName());
    	
    	Product p = productService.findByCode(	locale, 
												currency, 
												dto.getItemUPC());
    	
		b.addItem(p, dto.getItemQty());
		
    	bagService.save(b);
    	return ResponseEntity.ok(bagResourceAssembler.toModel(bagDTOMapper.doToDto(b)));
	}
 
    
}
