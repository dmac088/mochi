package io.nzbee.entity.product;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.nzbee.entity.ILocalizedDao;

public interface IProductDao extends ILocalizedDao<Product> {
	
	Page<Product> findAll(	String locale, 
							String currency, 
							Pageable pageable,
							String orderby);

	//with price range
	Page<Product> findAll( 
							String locale,
							String currency,
							Double priceStart, 
							Double priceEnd,
							Pageable pageable,
							String categoryCode,
							List<String> categoryCodes,
							List<String> brandCodes, 
							List<String> tagCodes, 
							String orderby);

	Page<Product> findAll(	
							String locale, 
							String currency,
							Pageable pageable,
							String categoryCode,
							List<String> categoryCodes, 
							List<String> brandCodes, 
							List<String> tagCodes,
							String orderby);

	
}
