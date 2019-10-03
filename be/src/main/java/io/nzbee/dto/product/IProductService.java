package io.nzbee.dto.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import io.nzbee.domain.services.IService;
import io.nzbee.dto.brand.Brand;
import io.nzbee.dto.category.Category;
import io.nzbee.dto.tag.Tag;

public interface IProductService extends IService<Product> {

	//Only domain objects should be returned from the below methods
	//User Interface objects should be returned from one or more DTO (data transfer object) services
	//This service layer should simply construct and persist "domain" objects from and to the entity layer
	//This service layer is an interface since if we want to store our data in a non-hibernate way, we can swap in another service 
	//as long as it conforms to the same interface contract
	
	//returns a domain object, this is good
	Optional<Product> findOne(String lcl, String currency, String code);

	Double getMaxPrice(String categoryDesc, String locale, String markdownSkuDescription, String currency,
			List<Category> categories, List<Brand> brands, List<Tag> tags);

	Page<Product> findAll(String locale, String currency, String categoryDesc, Double price, int page, int size,
			String sortBy, List<Category> categories, List<Brand> brands, List<Tag> tags);

	Long getCount(String categoryTypeCode, String categoryDesc, String locale, String currency,
			String productStatusCode, List<Category> categories, List<Brand> brands, List<Tag> tags);

	List<Product> findAll(String locale, String currency, List<String> productCodes);

	Page<Product> findAll(String locale, String currency, String categoryDesc, int page, int size, String sortBy,
			List<Category> categories, List<Brand> brands, List<Tag> tags);

	Product convertToProductDO(String productCreatedDate, String productUPC, String productDesc,
			Double productRetailPrice, Double productMarkdownPrice, String productImage, String productLocale,
			String productCurrency, String productCategory);

	

	
}
