package io.nzbee.entity.product;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.nzbee.entity.ILocalizedService;

public interface IProductService extends ILocalizedService<Product> {
	
	<T> List<Product> findAllByType(String locale, String currency, Class<T> cls);

	Page<Product> findAll(String locale, String currency, Pageable pageable, String categoryDesc,
			List<String> categoryCodes, List<String> brandCodes, List<String> tagCodes, Double maxPrice); 
}
