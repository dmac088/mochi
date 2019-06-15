package io.nzbee.entity;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProductTagRepository extends CrudRepository<ProductTag, Long> {

	List<ProductTag> findAll();
	
	List<ProductTag> findByProductsPricesPriceValueAndProductsPricesCurrencyCodeAndProductsPricesTypeDescAndProductsCategoriesHierarchyCodeAndProductsCategoriesCategoryIdInAndProductsBrandBrandIdIn(Double dummy, String currency, String priceType, String hierarchyCode, List<Long> categoryIds);
	
}
