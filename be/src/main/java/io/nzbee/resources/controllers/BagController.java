package io.nzbee.resources.controllers;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.IBagService;
import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.product.Product;
import io.nzbee.dto.bag.item.BagItemDTO;


@RestController
@RequestMapping("/api")
public class BagController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private IBagService bagService;
    
    @Autowired
	private IProductService productService;
    

    public BagController() {
        super();
    }

    @GetMapping("/Bag/{locale}/{currency}")
	public Bag getCustomerBag(	@PathVariable String locale, 
								@PathVariable String currency, 
								Principal principal) {
    	LOGGER.debug("call CustomerController.getCustomerBag");
    	return bagService.findByCode(locale,
    								 currency,
    								 principal.getName());
	}
    
    @PostMapping("/Bag")
	public Bag addItemToBag(@RequestBody BagItemDTO dto) {
    	LOGGER.debug("call CustomerController.addItemToBag");
    	//here we get the bag and bagItems but the products are null
    	Bag b = bagService.findByCode(	dto.getLocale(), 
    									dto.getCurrency(), 
    									dto.getBagUserName());
    	
    	Product p = productService.findByCode(	dto.getLocale(), 
												dto.getCurrency(), 
												dto.getItemUPC());
    	
		b.addItem(p, dto.getQty());
		
    	bagService.save(b);
    	return b;
	}
 
    
}
