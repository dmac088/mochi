package io.nzbee.entity.product;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.nzbee.entity.IDao;

public interface IProductDao extends IDao<Product> {

	Page<Product> findAll(String locale, String currency, List<String> productCodes);
	
	Page<Product> findAll(String locale, String currency, int page, int size, String orderby);

	Page<Product> findAll(List<String> categoryCodes, String locale, String priceType, String currency,
			Date priceDateStart, Date priceDateEnd, Pageable pageable, List<String> brandCodes, List<String> tagCodes);

	Page<Product> findAll(List<String> categoryCodes, String locale, Double priceStart, Double priceEnd,
			String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable,
			List<String> brandCodes, List<String> tagCodes);
}
