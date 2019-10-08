package io.nzbee.entity.product;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.nzbee.entity.IService;

public interface IProductService extends IService<Product> {
	
	Page<Product> findAll(String locale, String currency, List<String> productCodes);

	Page<Product> findAll(String locale, String currency, String priceType, int page, int size, String categoryDesc,
			List<String> categoryCodes, List<String> brandCodes, List<String> tagCodes);

	Page<Product> findAll(String locale, String currency, Double priceStart, Double priceEnd, String priceType,
			int page, int size, String categoryDesc, List<String> categoryCodes, List<String> brandCodes,
			List<String> tagCodes);

	
}
