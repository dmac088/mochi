package io.nzbee.domain.category;


import java.util.Set;
import io.nzbee.domain.IProductDimensionService;


public interface ICategoryService extends IProductDimensionService<Category> {

	Set<Category> findAll(String locale, String currency);

	Set<Category> findByParent(String locale, String currency, String parentCategoryCode);

	Set<Category> findAllForLevel(String locale, String currency, Long level);

	Set<ProductCategory> findAllByProductCode(String locale, String currency, String code);

	
}
