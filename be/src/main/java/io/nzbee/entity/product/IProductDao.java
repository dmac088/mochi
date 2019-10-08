package io.nzbee.entity.product;

import java.util.List;
import org.springframework.data.domain.Page;

import io.nzbee.entity.IDao;

public interface IProductDao extends IDao<Product> {

	Page<Product> findAll(String locale, String currency, List<String> productCodes);
	
	Page<Product> findAll(	String locale, 
							String currency, 
							int page, 
							int size, 
							String orderby);

	//with price range
	Page<Product> findAll( 
							String locale,
							String currency,
							Double priceStart, 
							Double priceEnd,
							int page, 
							int size,
							String categoryCode,
							List<String> categoryCodes,
							List<String> brandCodes, 
							List<String> tagCodes, 
							String orderby);

	Page<Product> findAll(	
							String locale, 
							String currency,
							int page, 
							int size, 
							String categoryCode,
							List<String> categoryCodes, 
							List<String> brandCodes, 
							List<String> tagCodes,
							String orderby);

}
