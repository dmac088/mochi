package io.nzbee.dto.product;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.nzbee.dto.IDto;
import io.nzbee.dto.ILocalizedService;

public interface IProductService  extends ILocalizedService<io.nzbee.dto.product.Product, 
															io.nzbee.entity.product.Product,
															io.nzbee.domain.product.Product> {

	//Only domain objects should be returned from the below methods
	//User Interface objects should be returned from one or more DTO (data transfer object) services
	//This service layer should simply construct and persist "domain" objects from and to the entity layer
	//This service layer is an interface since if we want to store our data in a non-hibernate way, we can swap in another service 
	//as long as it conforms to the same interface contract

	List<Product> findAll(	String locale, 
							String currency, 
							List<String> productCodes);

	Page<Product> findAll(	String locale, 
							String currency, 
							Double price, 
							Pageable pageable, 
							String categoryDesc,
							List<IDto> ldto, 
							String sortBy);

	Page<Product> findAll(	String locale, 
							String currency, 
							Pageable pageable, 
							String categoryDesc,
							List<IDto> ldto, 
							String sortBy);
	
}
