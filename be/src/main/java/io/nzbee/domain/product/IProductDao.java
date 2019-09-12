package io.nzbee.domain.product;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.nzbee.domain.IDao;

public interface IProductDao extends IDao<Product> {

	Double getMaxPriceByCode(String categoryDesc, String locale, String priceType, String currency,
			List<String> categoryCodes, List<String> brandCodes, List<String> tagCodes);

	Double getMaxPriceById(String categoryDesc, String locale, String priceType, String currency,
			List<Long> categoryIds, List<Long> brandIds, List<Long> tagIds);

	Page<Product> findAll(List<String> categoryCodes, String locale, Double priceStart, Double priceEnd,
			String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable,
			List<String> brandCodes, List<String> tagCodes);

	List<Product> getAll(String locale, String currency, List<String> productCodes);

	Page<Product> findAll(List<String> categoryCodes, String locale, String priceType, String currency,
			Date priceDateStart, Date priceDateEnd, Pageable pageable, List<String> brandCodes, List<String> tagCodes);

	List<Product> findAll(String locale, String currency);

	Optional<Product> findById(long id, String locale, String currency);

	List<Product> getAll(String locale, String currency);

	Optional<Product> findByUPC(String upc, String locale, String currency);
	
}
