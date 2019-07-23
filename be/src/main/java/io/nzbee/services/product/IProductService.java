package io.nzbee.services.product;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.nzbee.domain.Product;
import io.nzbee.dto.SearchDTO;
import io.nzbee.dto.sidebar.SidebarDTO;

public interface IProductService {

	//Only domain objects should be returned from the below methods
	//User Interface objects should be returned from one or more DTO (data transfer object) services
	//This service layer should simply construct and persist "domain" objects from and to the entity layer
	//This service layer is an interface since if we want to store our data in a non-hibernate way, we can swap in another service 
	//as long as it conforms to the same interface contract
	
	//returns a user interface object, rule broken, need to change to return a domain object 
	SearchDTO findProduct(String locale, String currency, String categoryCode, String term, int page, int size, String sortBy, List<SidebarDTO> selectedFacets);
	
	//returns a user interface object, rule broken, need to change to return a domain object 
	SearchDTO getProducts(String locale, String currency, String categoryDesc, Double price, int page, int size, String sortBy, List<SidebarDTO> selectedFacets);
	 
	//returns a list of domain object, this is good
	List<Product> getProducts(@PathVariable String locale, @PathVariable String currency, @RequestBody final List<Long> productIds);
	 
	//returns a domain object, this is good
	Product getProduct(String locale, String currency, Long id);
	 
	//returns a user interface object, rule broken, need to change to return a domain object 
	List<SidebarDTO> getProductTags(String locale, String currency, String categoryDesc, Double price, List<SidebarDTO> selectedFacets);
	 
	//returns a user interface object, rule broken, need to change to return a domain object
	List<SidebarDTO> getProductTags(String locale, String currency, String categoryDesc, List<SidebarDTO> selectedFacets);

	//returns an attribute value which is allowed
	Double getMaxPrice(String categoryDesc, String locale, String currency, List<SidebarDTO> selectedFacets);
	 
}
