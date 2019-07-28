package io.nzbee.domain.services.product;

import java.util.List;

import org.springframework.data.domain.Page;
import io.nzbee.domain.Product;
import io.nzbee.domain.services.IService;

public interface IProductService extends IService<Product> {

	//Only domain objects should be returned from the below methods
	//User Interface objects should be returned from one or more DTO (data transfer object) services
	//This service layer should simply construct and persist "domain" objects from and to the entity layer
	//This service layer is an interface since if we want to store our data in a non-hibernate way, we can swap in another service 
	//as long as it conforms to the same interface contract
	
	//returns a user interface object, rule broken, need to change to return a domain object 
	Page<Product> findAll(String lcl, 
			 String currency, 
			 String categoryDesc, 
			 String searchTerm, 
			 int page, 
			 int size, 
			 String sortBy, 
			 List<String> categoryTokens,
			 List<String> brandTokens,
			 List<String> tagTokens);
	
	//returns a user interface object, rule broken, need to change to return a domain object 
	Page<Product> findAll(String locale, 
			 String currency, 
			 String categoryDesc, 
			 Double price, 
			 int page, 
			 int size, 
			 String sortBy, 
			 List<Long> categoryIds,
			 List<Long> brandIds,
			 List<Long> tagIds);
	
	//returns a domain object, this is good
	Product findOne(String locale, String currency, Long id);
	
	List<Product> findAll(String locale, String currency, List<Long> productIds);

	Double getMaxPrice(String categoryDesc, String locale, String markdownSkuDescription, String currency,
			List<Long> categoryIds, List<Long> brandIds, List<Long> tagIds);
	
	Long getCount(String categoryTypeCode, String categoryDesc, String locale, String currency,
			String productStatusCode, List<Long> categoryIds, List<Long> brandIds,
			List<Long> tagIds
			);
	
}
