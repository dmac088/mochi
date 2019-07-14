package io.nzbee.entity.tag;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProductTagRepository extends CrudRepository<ProductTag, Long> {

	List<ProductTag> findAll();
	
	List<ProductTag> findByProductsPricesPriceValueLessThanEqualAndProductsPricesCurrencyCodeAndProductsPricesTypeDescAndProductsCategoriesHierarchyCodeAndProductsCategoriesCategoryIdInAndProductsBrandBrandIdIn(Double dummy, String currency, String priceType, String hierarchyCode, List<Long> categoryIds, List<Long> brandIds);
	
	List<ProductTag> findByProductsPricesPriceValueLessThanEqualAndProductsPricesCurrencyCodeAndProductsPricesTypeDescAndProductsCategoriesHierarchyCodeAndProductsCategoriesCategoryIdIn(Double dummy, String currency, String priceType, String hierarchyCode, List<Long> categoryIds);
	
}
