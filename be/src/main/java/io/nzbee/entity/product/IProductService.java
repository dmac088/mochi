package io.nzbee.entity.product;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.nzbee.entity.IService;

public interface IProductService extends IService<Product> {

	Long getCount(String categoryDesc, String locale, String productStatusCode, List<String> brandCodes,
			List<String> categoryCodes);

	Long getCountForTags(String categoryDesc, String locale, String productStatusCode, List<String> brandCodes,
			List<String> categoryCodes, List<String> tagCodes);

	Double getMaxMarkDownPrice(String categoryTypeCode, String categoryDesc, String locale, String currencyCode, 
			String productStatusCode, List<String> brandCodes, List<String> categoryCodes);

	Double getMaxMarkDownPriceForTags(String categoryTypeCode, String categoryDesc, String locale, String currencyCode,
			String productStatusCode, List<String> brandCodes, List<String> categoryCodes, List<String> tagCodes);
	
	Double getMaxMarkDownPriceForCategory(String categoryCode, String currencyCode);

	Long getCountForCategory(String categoryCode);

	Page<Product> findAll(String categoryDesc, List<String> categoryCodes, String locale, Double priceStart,
			Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd,
			Pageable pageable, List<String> brandCodes, List<String> tagCodes);

	Page<Product> findAll(String categoryDesc, List<String> categoryCodes, String locale, String priceType,
			String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable, List<String> brandCodes,
			List<String> tagCodes);


	List<Product> findAll(String locale, String currency, List<String> productCodes);

	
}
