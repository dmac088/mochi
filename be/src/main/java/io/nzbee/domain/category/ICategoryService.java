package io.nzbee.domain.category;


import java.util.Set;
import io.nzbee.domain.IService;


public interface ICategoryService extends IService<Category> {

	Set<Category> findAll(String locale, String currency);

	Set<Category> findByParent(String locale, String currency, String parentCategoryCode);

	Set<Category> findAllForLevel(String locale, String currency, Long level);

	Set<ProductCategory> findAllByProductCode(String locale, String currency, String code);

	
}
