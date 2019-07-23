package io.nzbee.entity.product;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {

	public List<Product> getProducts();
	
	public Page<Product> getProducts(List<Long> categoryIds, String locale, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable, List<Long> brandIds, List<Long> tagIds);

	public Optional<Product> getProduct(Long Id);
	
	public Optional<Product> getProduct(String upc);
	
	Double maxMarkDownPrice(String categoryTypeCode,
		   	String categoryDesc, 
		   	String locale,
		   	String currencyCode,
		   	String priceTypeDesc,
		   	String productStatusCode,
		   	List<Long> brandIds,
		   	int inHandlingBrands,
		   	List<Long> categoryIds,
		   	int inHandlingCategories);
	
	Double maxMarkDownPriceForTags(String categoryTypeCode,
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
	
	Long count(
		   	String categoryTypeCode,
		   	String categoryDesc, 
		   	String locale,
		   	String currencyCode,
		   	String priceTypeDesc,
		   	String productStatusCode,
		   	List<Long> brandIds,
		   	int inHandlingBrands,
		   	List<Long> categoryIds,
		   	int inHandlingCategories);
	
	
	Long countForTags(
			String categoryTypeCode,
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
}
