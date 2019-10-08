package io.nzbee.dto.product;

import java.util.List;
import org.springframework.data.domain.Page;
import io.nzbee.dto.IService;
import io.nzbee.dto.brand.Brand;
import io.nzbee.dto.category.Category;
import io.nzbee.dto.tag.Tag;

public interface IProductService extends IService<Product> {

	//Only domain objects should be returned from the below methods
	//User Interface objects should be returned from one or more DTO (data transfer object) services
	//This service layer should simply construct and persist "domain" objects from and to the entity layer
	//This service layer is an interface since if we want to store our data in a non-hibernate way, we can swap in another service 
	//as long as it conforms to the same interface contract

	List<Product> findAll(String locale, String currency, List<String> productCodes);

	Page<Product> findAll(String locale, String currency, Double price, int page, int size, String categoryDesc,
			List<Category> categories, List<Brand> brands, List<Tag> tags, String sortBy);

	Page<Product> findAll(String locale, String currency, int page, int size, String categoryDesc,
			List<Category> categories, List<Brand> brands, List<Tag> tags, String sortBy);
	
}
