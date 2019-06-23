package io.nzbee.entity;


import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductPagingAndSortingRepository extends PagingAndSortingRepository<Product, Long> {

	Page<Product> findAll(Pageable pageable);

	Page<Product> findByCategoriesCategoryId(Long categoryId, Pageable pageable);
	
	Page<Product> findByCategoriesCategoryIdInAndAttributesLclCd(List<Long> categoryIdsAndAttributesLclCd, String lcl, Pageable pageable);
	
	Page<Product> findByCategoriesCategoryIdInAndAttributesLclCdAndPricesPriceValueBetween(List<Long> categoryIds, String lcl, Double priceStart, Double priceEnd, Pageable pageable);
	
	Page<Product> findByCategoriesCategoryIdInAndAttributesLclCdAndBrandBrandAttributesBrandDescAndBrandBrandAttributesLclCd(List<Long> categoryIds, String productlcl, String brandDesc, String brandlcl, Pageable pageable);
	
	Page<Product> findByCategoriesCategoryIdInAndAttributesLclCdAndPricesPriceValueBetweenAndPricesTypeDescAndPricesCurrencyCodeAndPricesStartDateLessThanAndPricesEndDateGreaterThan(List<Long> categoryIds, String lcl, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable);
	
	Page<Product> findByCategoriesCategoryIdInAndAttributesLclCdAndBrandBrandAttributesLclCdAndPricesPriceValueBetweenAndPricesTypeDescAndPricesCurrencyCodeAndPricesStartDateLessThanAndPricesEndDateGreaterThan(List<Long> categoryIds, String productlcl, String brandlcl, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable);
	
	//Page<Product> findByCategoriesCategoryIdInAndAttributesLclCdAndBrandBrandAttributesLclCdAndPricesPriceValueBetweenAndPricesTypeDescAndPricesCurrencyCodeAndPricesStartDateLessThanAndPricesEndDateGreaterThanAndBrandBrandIdIn(List<Long> categoryIds, String productlcl, String brandlcl, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable, List<Long> brandIds);

}
