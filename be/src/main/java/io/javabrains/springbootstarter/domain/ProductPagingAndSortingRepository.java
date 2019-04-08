package io.javabrains.springbootstarter.domain;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductPagingAndSortingRepository extends PagingAndSortingRepository<Product, Long> {

	Page<Product> findAll(Pageable pageable);

	Page<Product> findByCategoriesCategoryId(Long categoryId, Pageable pageable);
	
	Page<Product> findByCategoriesCategoryIdIn(List<Long> categoryIds, Pageable pageable);
	
	Page<Product> findByCategoriesCategoryIdInAndPricesPriceValueBetween(List<Long> categoryIds, Long priceStart, Long priceEnd, Pageable pageable);
	
	Page<Product> findByCategoriesCategoryIdInAndBrandBrandAttributesBrandDesc(List<Long> categoryIds, String brandDesc, Pageable pageable);
	
	Page<Product> findByCategoriesCategoryIdInAndPricesPriceValueBetweenAndPricesTypeDescAndPricesCurrencyAndPricesStartDateLessThanAndPricesEndDateGreaterThan(List<Long> categoryIds, Long priceStart, Long priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable);
	
	Page<Product> findByCategoriesCategoryIdInAndBrandBrandAttributesBrandDescAndPricesPriceValueBetweenAndPricesTypeDescAndPricesCurrencyCodeAndPricesStartDateLessThanAndPricesEndDateGreaterThan(List<Long> categoryIds, String brandDesc, Long priceStart, Long priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable);

}
