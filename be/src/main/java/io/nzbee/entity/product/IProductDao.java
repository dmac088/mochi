package io.nzbee.entity.product;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.nzbee.entity.IDao;

public interface IProductDao extends IDao<Product> {

	Optional<Product> findByUPC(String upc);
	
	//parameters should be primitive types
	Page<Product> findAllActiveSKU(List<Long> categoryIds, String locale, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable, List<Long> brandIds, List<Long> tagIds);	
	
	List<Product> getAll(String locale, String currency, List<Long> productIds);

	Page<Product> findAllActiveSKUByPrimaryHierarchyById(List<Long> categoryIds, String locale, Double priceStart,
			Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd,
			Pageable pageable, List<Long> brandIds, List<Long> tagIds);

	Double getMaxPriceByCode(String categoryDesc, String locale, String priceType, String currency,
			List<String> categoryCodes, List<String> brandCodes, List<String> tagCodes);

	Double getMaxPriceById(String categoryDesc, String locale, String priceType, String currency,
			List<Long> categoryIds, List<Long> brandIds, List<Long> tagIds);

	Page<Product> findAllActiveSKUByCode(List<String> categoryCodes, String locale, Double priceStart, Double priceEnd,
			String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable,
			List<String> brandCodes, List<String> tagCodes);
	
}
