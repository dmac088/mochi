package io.nzbee.entity.product;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.nzbee.entity.ILocalizedDao;

public interface IProductDao extends ILocalizedDao<Product> {
	
	Page<Product> findAll(	String locale, 
							String currency, 
							Pageable pageable,
							String orderby);

	<T> List<Product> findAllByType(String locale, String currency, Class<T> cls);


	Page<Product> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort);

	

	
}
