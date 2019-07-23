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
	Page<Product> findAll(List<Long> categoryIds, String locale, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable, List<Long> brandIds, List<Long> tagIds);	
	
	List<Product> getAll(String locale, String currency, List<Long> productIds);
	
	Double getMaxPrice(String categoryDesc, String locale, String priceType, String currency, List<Long> categoryIds, List<Long> brandIds, List<Long> tagIds);
	
}
