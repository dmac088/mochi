package io.nzbee.entity.product;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.nzbee.entity.IService;

public interface IProductService extends IService<Product> {
	
	Double getMaxMarkDownPrice(String categoryTypeCode, String categoryDesc, String locale, String currencyCode, 
			String productStatusCode, List<String> brandCodes, List<String> categoryCodes);

	Double getMaxMarkDownPriceForTags(String categoryTypeCode, String categoryDesc, String locale, String currencyCode,
			String productStatusCode, List<String> brandCodes, List<String> categoryCodes, List<String> tagCodes);

	Long getCount(String categoryDesc, String locale, String currency, String productStatusCode,
			List<String> brandCodes, List<String> categoryCodes);

	Long getCountForTags(String categoryDesc, String locale, String currency, String productStatusCode,
			List<String> brandCodes, List<String> categoryCodes, List<String> tagCodes);

	Page<Product> findAll(String locale, String currency, List<String> productCodes);
	
	Page<Product> findAll(String locale, String currency, String priceType, Pageable pageable, String categoryDesc,
			List<String> categoryCodes, List<String> brandCodes, List<String> tagCodes);

	Page<Product> findAll(String locale, String currency, Double priceStart, Double priceEnd, String priceType,
			Pageable pageable, String categoryDesc, List<String> categoryCodes, List<String> brandCodes,
			List<String> tagCodes);

	
}
