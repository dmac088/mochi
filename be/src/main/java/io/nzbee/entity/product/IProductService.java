package io.nzbee.entity.product;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.nzbee.entity.IService;

public interface IProductService extends IService<Product> {

	
	public Page<Product> findAll(List<Long> categoryIds, String locale, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable, List<Long> brandIds, List<Long> tagIds);

	List<Product> findAll(String locale, String currency, List<Long> productIds);
	
	Double getMaxPrice(String categoryDesc, String locale, String priceType, String currency, List<Long> categoryIds, List<Long> brandIds, List<Long> tagIds);
	
	Double getMaxMarkDownPrice(String categoryTypeCode,
		   	String categoryDesc, 
		   	String locale,
		   	String currencyCode,
		   	String priceTypeDesc,
		   	String productStatusCode,
		   	List<Long> brandIds,
		   	int inHandlingBrands,
		   	List<Long> categoryIds,
		   	int inHandlingCategories);
	
	Double getMaxMarkDownPriceForTags(String categoryTypeCode,
			String categoryDesc, 
			String locale,
			String currencyCode,
			String priceTypeDesc,
			String productStatusCode,
			List<Long> brandIds,
			int inHandlingBrands,
			List<Long> categoryIds,
			int inHandlingCategories,
			List<Long> tagIds,
			int inHandlingTags);

	Long getCount(String categoryDesc, String locale, String productStatusCode, List<Long> brandIds,
			int inHandlingBrands, List<Long> categoryIds, int inHandlingCategories);

	Long getCountForTags(String categoryDesc, String locale, String productStatusCode, List<Long> brandIds,
			int inHandlingBrands, List<Long> categoryIds, int inHandlingCategories, List<Long> tagIds,
			int inHandlingTags);
}
