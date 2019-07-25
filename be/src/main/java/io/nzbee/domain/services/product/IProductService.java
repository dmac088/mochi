package io.nzbee.domain.services.product;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.nzbee.domain.Product;
import io.nzbee.domain.services.IService;
import io.nzbee.ui.component.web.search.SearchDto;
import io.nzbee.ui.component.web.sidebar.SidebarDto;

public interface IProductService extends IService<Product> {

	//Only domain objects should be returned from the below methods
	//User Interface objects should be returned from one or more DTO (data transfer object) services
	//This service layer should simply construct and persist "domain" objects from and to the entity layer
	//This service layer is an interface since if we want to store our data in a non-hibernate way, we can swap in another service 
	//as long as it conforms to the same interface contract
	
	//returns a user interface object, rule broken, need to change to return a domain object 
	SearchDto findAll(String locale, String currency, String categoryCode, String term, int page, int size, String sortBy, List<SidebarDto> selectedFacets);
	
	//returns a user interface object, rule broken, need to change to return a domain object 
	SearchDto findAll(String locale, String currency, String categoryDesc, Double price, int page, int size, String sortBy, List<SidebarDto> selectedFacets);
	 
	//returns a list of domain object, this is good
	List<Product> findAll(@PathVariable String locale, @PathVariable String currency, @RequestBody final List<Long> productIds);
	 
	//returns a domain object, this is good
	Product findOne(String locale, String currency, Long id);
	 
	//returns a user interface object, rule broken, need to change to return a domain object 
	List<SidebarDto> getProductTags(String locale, String currency, String categoryDesc, Double price, List<SidebarDto> selectedFacets);
	 
	//returns a user interface object, rule broken, need to change to return a domain object
	List<SidebarDto> getProductTags(String locale, String currency, String categoryDesc, List<SidebarDto> selectedFacets);

	//returns an attribute value which is allowed
	Double getMaxPrice(String categoryDesc, String locale, String currency, List<SidebarDto> selectedFacets);
	
}
